package com.vip.abnerming.find;

import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * Created by zhengjin on 17/3/14.
 * 初始化一些状态,实现
 */

public class ViewUtils {
    /**
     * 保存传入的activity
     */
    private static Class<?> activityClass;

    /**
     * 初始化activity和所有注解
     *
     * @param obj 你需要初始化的activity
     */
    public static void inject(Object obj) {
        activityClass = obj.getClass();
        injectContent(obj);
        injectView(obj);
        inijectOnClick(obj);
    }

    // 初始化activity布局文件
    private static void injectContent(Object obj) {
        FindViewByIdLayout annotation = activityClass
                .getAnnotation(FindViewByIdLayout.class);
        if (annotation != null) {
            // 获取注解中的对应的布局id 因为注解只有个方法 所以@XXX(YYY)时会自动赋值给注解类唯一的方法
            int id = annotation.value();
            try {
                // 得到activity中的方法 第一个参数为方法名 第二个为可变参数 类型为 参数类型的字节码
                Method method = activityClass.getMethod("setContentView",
                        int.class);
                // 调用方法 第一个参数为哪个实例去调用 第二个参数为 参数
                method.invoke(obj, id);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }


    /**
     * 初始化activity中的所有view控件
     */
    private static void injectView(Object activityOrFragment) {
        // 对象所有的属性
        Field[] declaredFields = null;
        // 健壮性
        if (activityClass != null) {
            // 获取du所有的属性 包含私有 保护 默认 共开 但不包含继承等
            // getFields可以获取到所有公开的包括继承的 但无法获取到私有的属性
            declaredFields = activityClass.getDeclaredFields();
        }
        // 健壮性
        if (declaredFields != null) {
            // 遍历所有的属性变量
            for (Field field : declaredFields) {
                // 获取属性变量上的注解
                FindViewById annotation = field.getAnnotation(FindViewById.class);
                // 如果此属性变量 包含FMYViewView
                if (annotation != null) {
                    // 获取属性id值
                    int id = annotation.value();
                    Object obj = null;
                    try {
                        // 获取activity中方法
                        obj = activityClass.getMethod("findViewById",
                                int.class).invoke(activityOrFragment, id);
                        // 设置属性变量 指向实例
                        // 如果修饰符不为公共类 这里注意了 当activity
                        // 控件变量为private的时候 我们去访问会失败的 要么打破封装系 要么变量改为public
                        //如 private TextView tv 这种情况 如果不打破封装会直接异常
                        if (Modifier.PUBLIC != field.getModifiers()) {
                            // 打破封装性
                            field.setAccessible(true);
                        }
                        // 这里相当于 field= acitivity.obj
                        field.set(activityOrFragment, obj);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
        }

    }


    /**
     * 初始化所有控件的点击事件 只需要某方法上写上对应注解和id即可
     *
     * @param
     */
    private static void inijectOnClick(Object activityOrFragment) {
        //获得所有方法
        Method[] methods = null;
        methods = activityClass.getMethods();
        // 遍历所有的activity下的方法
        for (Method method : methods) {
            // 获取方法的注解
            OnClick fmyClickView = method
                    .getAnnotation(OnClick.class);
            // 如果存在此注解
            if (fmyClickView != null) {
                // 所有注解的控件的id
                int[] ids = fmyClickView.value();
                // 代理处理类
                MInvocationHandler handler = new MInvocationHandler(activityOrFragment,
                        method);
                // 代理实例 这里也可以返回     new Class<?>[] { View.OnClickListener.class }中的接口类
                //第一个参数用于加载其他类 不一定要使用View.OnClickListener.class.getClassLoader() 你可以使用其他的
                //第二个参数你所实现的接口
                Object newProxyInstance = Proxy.newProxyInstance(
                        View.OnClickListener.class.getClassLoader(),
                        new Class<?>[]{View.OnClickListener.class}, handler);
                // 遍历所有的控件id 然后设置代理
                for (int i : ids) {
                    try {
                        Object view = null;
                        //如果对象是activity
                        view = activityClass.getMethod("findViewById",
                                int.class).invoke(activityOrFragment, i);
                        if (view != null) {
                            Method method2 = view.getClass().getMethod(
                                    "setOnClickListener",
                                    View.OnClickListener.class);
                            method2.invoke(view, newProxyInstance);
                        }
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
            }
        }

    }

    static class MInvocationHandler implements InvocationHandler {
        //这里我们到时候回传入activity
        private Object target;
        // 用户自定义view 的点击事件方法
        private Method method;

        public MInvocationHandler(Object target, Method method) {
            super();
            this.target = target;
            this.method = method;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            // 调用用户自定义方法的点击事件 让activity调用中开发者设定的方法
            return this.method.invoke(target, args);
        }

    }
}
