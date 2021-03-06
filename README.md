# AbnerAndroid

### AbnerAndroid依赖库使用说明：

###### 2017/12/29主要增加，MVP模式

    怎样从架构级别去搭建一个APP，怎样让他应对日益更改的界面与业务逻辑？
    越小的类，bug越不容易出现，越容易调试，更容易测试，我相信这一点大家是都赞同的。在MVP模式下，View和Model是完全分离没有任何直接关联的(比如你在View层中完全不需要导Model的包，也不应该去关联它们)。使用MVP模式能够更方便的帮助Activity(或Fragment)职责分离，减小类体积，使项目结构更加清晰。

    具体使用参考包：mvp/test/下具体实例


## 一、【请求网络】
##### 设置常用URL
         Retrofit retrofit = HttpRetrofit.getmHttpRetrofit().initRetrofit(BaseApi.HTTP_URL_1);
         listenerApi_1 = retrofit.create(ApiListener.class);

         Retrofit retrofit = HttpRetrofit.getmHttpRetrofit().initRetrofit(BaseApi.HTTP_URL_2);
         listenerApi_2 = retrofit.create(ApiListener.class);

         ......

##### 具体使用如下,在使用得地方，新创建类，构造方法初始化（返回字符串使用HttpCallback，返回javabean使用HttpBeanCallback）

            public class Test {
                private  ApiListener apiListener;
                public Test(){
                   apiListener= HttpHelper.getApiListener();
                }
                //请求网络方法，结构返回状态，成功或失败
                public void getUserInFo(final Context ctx, String name, String pass, final HttpHelper.UserLoginListener listener) {
                    apiListener.getUserInFo(name, pass).enqueue(new HttpCallback(){
                        @Override
                        protected void onSuccess(String mess) {
                            listener.loginOk();
                        }
                        @Override
                        protected void onFailure() {
                            listener.cancle();
                        }
                    });
                }
                public interface UserLoginListener {
                    void loginOk();
                    void cancle();
                }
            }

##### 具体类型请求
            （POST请求）
            @POST("LoginUser.php")
            Call<ResponseBody> getUserInFo(@Query("user_name") String userName, @Query("user_pass") String user_pass);

           （ GET请求）
            @GET("week/txt/study_message.txt")
            Call<ResponseBody> getStudyList();

            （上传文件）
            @Multipart
            @POST("upload_image")
            Call<ResponseBody> upload(@Query("user_hidden") String user_hidden,
                                      @Part MultipartBody.Part file);

## 二、继承使用，activity继承与BaseActivity,fragment继承与BaseFragment：
    方法说明：
        setTitle 设置标题
        showBack 是否显示返回键
        showTitle 是否显示标题

## 三、注解使用,有两个注解
    1、使用包find下
        初始化：最好在父类中，在当前类也可以：ViewUtils.inject(this);

        注解控件
            @FindViewById(R.id.login_user_name)
            EditText mUserName;

         注解事件
             @OnClick(R.id.account_out_layout)
             public void accountClick(View view) {

             }

    2、使用包view
        初始化：最好在父类中，在当前类也可以：ViewUtils.inject(this);

## 四、引导页，轮播页，指示器使用：
    1、正常引导页使用
    （布局-------------------------------------------------------------------）
            <com.zhengsr.viewpagerlib.view.GlideViewPager
                android:id="@+id/splase_viewpager"
                android:layout_width="match_parent"
                android:layout_height="match_parent"/>

            <Button
                android:id="@+id/splase_start_btn"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_alignParentBottom="true"
                android:layout_marginLeft="100dp"
                android:layout_marginRight="100dp"
                android:layout_marginBottom="50dp"
                android:background="@drawable/glide_bottom_btn_bg"
                android:textColor="@color/white"
                android:text="@string/start"
                android:textSize="18sp"
                android:visibility="gone"
                />
            <!--指示器-->
            <com.zhengsr.viewpagerlib.indicator.NormalIndicator
                android:id="@+id/splase_bottom_layout"
                android:layout_alignParentBottom="true"
                android:layout_width="match_parent"
                android:layout_height="50dp"
                android:gravity="center"
                zsr:normal_leftmargin="10dp"
                zsr:normal_dismiss_open="true"
                zsr:normal_selector="@drawable/glide_bottom_selector"
                android:orientation="horizontal"/>

            代码实现-------------------------------------------------------------------：

            //初始化轮播图片
             private static final Integer[] RES = {R.mipmap.guide1,R.mipmap.guide2,R.mipmap.guide3,
               R.mipmap.guide4};

             //先把本地的图片 id 装进 list 容器中
                    List<Integer> images = new ArrayList<>();
                    for (int i = 0; i < RES.length; i++) {
                        images.add(RES[i]);

                    }
               //配置pagerbean，这里主要是为了viewpager的指示器的作用，注意记得写上泛型
                PageBean bean = new PageBean.Builder<Integer>()
                            .setDataObjects(images)
                            .setIndicator(linearLayout)
                            .setOpenView(button)
                            .builder();

                // 把数据添加到 viewpager中，并把view提供出来，这样除了方便调试，也不会出现一个view，多个
                // parent的问题，这里在轮播图比较明显
                    viewPager.setPageListener(bean, R.layout.image_layout, new PageHelperListener() {
                        @Override
                        public void getItemView(View view, Object data) {
                            ImageView imageView = (ImageView) view.findViewById(R.id.icon);
                            imageView.setImageResource((Integer) data);
                        }
                    });

      2、放大引导页使用
        （布局-------------------------------------------------------------------）
         <com.zhengsr.viewpagerlib.view.GlideViewPager
                android:id="@+id/splase_viewpager"
                android:layout_width="match_parent"
                android:layout_height="match_parent"/>

            <Button
                android:id="@+id/splase_start_btn"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_alignParentBottom="true"
                android:layout_marginLeft="100dp"
                android:layout_marginRight="100dp"
                android:layout_marginBottom="50dp"
                android:background="@drawable/glide_bottom_btn_bg"
                android:textColor="@color/white"
                android:text="@string/start"
                android:textSize="18sp"
                android:visibility="gone"
                />

           <com.zhengsr.viewpagerlib.indicator.ZoomIndicator
                android:id="@+id/splase_bottom_layout"
                android:layout_alignParentBottom="true"
                android:layout_width="match_parent"
                android:layout_height="50dp"
                android:gravity="center"
                zsr:zoom_leftmargin="10dp"
                zsr:zoom_max="1.5"
                zsr:zoom_alpha_min="0.4"
                zsr:zoom_dismiss_open="true"
                android:orientation="horizontal"/>

         代码实现-------------------------------------------------------------------：

           //先把本地的图片 id 装进 list 容器中
                  List<Integer> images = new ArrayList<>();
                  for (int i = 0; i < RES.length; i++) {
                      images.add(RES[i]);

                  }
                  //配置pagerbean，这里主要是为了viewpager的指示器的作用，注意记得写上泛型
                  PageBean bean = new PageBean.Builder<Integer>()
                          .setDataObjects(images)
                          .setIndicator(zoomIndicator)
                          .setOpenView(button)
                          .builder();

                  // 把数据添加到 viewpager中，并把view提供出来，这样除了方便调试，也不会出现一个view，多个
                  // parent的问题，这里在轮播图比较明显
                  viewPager.setPageListener(bean, R.layout.image_layout, new PageHelperListener() {
                      @Override
                      public void getItemView(View view, Object data) {
                          //通过获取到这个view，你可以随意定制你的内容
                          ImageView imageView = (ImageView) view.findViewById(R.id.icon);
                          imageView.setImageResource((Integer) data);
                      }
                  });
    3、移动引导页实现：

        布局-------------------------------------------------------------------

        <com.zhengsr.viewpagerlib.view.GlideViewPager
                android:id="@+id/splase_viewpager"
                android:layout_width="match_parent"
                android:layout_height="match_parent"/>

            <Button
                android:id="@+id/splase_start_btn"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_alignParentBottom="true"
                android:layout_marginLeft="100dp"
                android:layout_marginRight="100dp"
                android:layout_marginBottom="50dp"
                android:background="@drawable/glide_bottom_btn_bg"
                android:textColor="@color/white"
                android:text="@string/start"
                android:textSize="18sp"
                android:visibility="gone"
                />

            <com.zhengsr.viewpagerlib.indicator.TransIndicator
                android:id="@+id/splase_bottom_layout"
                android:layout_alignParentBottom="true"
                android:layout_width="match_parent"
                android:layout_height="50dp"
                zsr:trans_leftmargin="10dp"
                zsr:trans_movecolor="@color/colorAccent"
                zsr:trans_defaultcolor="@color/gray_cccc"
                zsr:trans_type="circle"
                zsr:trans_width="5dp"
                zsr:trans_height="5dp"
                zsr:trans_dismiss_open="true"
                android:orientation="horizontal"/>

         代码实现-------------------------------------------------------------------

           //先把本地的图片 id 装进 list 容器中
                 List<Integer> images = new ArrayList<>();
                 for (int i = 0; i < RES.length; i++) {
                     images.add(RES[i]);

                 }
                 //配置pagerbean，这里主要是为了viewpager的指示器的作用，注意记得写上泛型
                 PageBean bean = new PageBean.Builder<Integer>()
                         .setDataObjects(images)
                         .setIndicator(linearLayout)
                         .setOpenView(button)
                         .builder();

                 // 把数据添加到 viewpager中，并把view提供出来，这样除了方便调试，也不会出现一个view，多个
                 // parent的问题，这里在轮播图比较明显
                 viewPager.setPageListener(bean, R.layout.image_layout, new PageHelperListener() {
                     @Override
                     public void getItemView(View view, Object data) {
                         ImageView imageView = (ImageView) view.findViewById(R.id.icon);
                         imageView.setImageResource((Integer) data);
                     }
                 });

     轮播图：

           放大版，自动轮播加魅族效果

          <com.zhengsr.viewpagerlib.view.BannerViewPager
                         android:id="@+id/loop_viewpager"
                         android:layout_width="match_parent"
                         android:layout_height="match_parent"
                         android:layout_marginLeft="20dp"
                         android:layout_marginRight="20dp"
                         android:clipChildren="false"
                         zsr:banner_isloop="true"
                         zsr:banner_looptime="3000"
                         zsr:banner_switchtime="600" />

                     <com.zhengsr.viewpagerlib.indicator.ZoomIndicator
                         android:id="@+id/bottom_scale_layout"
                         android:layout_width="wrap_content"
                         android:layout_height="30dp"
                         android:layout_gravity="bottom|right"
                         android:layout_marginRight="20dp"
                         android:gravity="center"
                         zsr:zoom_alpha_min="0.5"
                         zsr:zoom_leftmargin="10dp"
                         zsr:zoom_max="1.5"
                         zsr:zoom_selector="@drawable/bottom_circle" />

           放大版，弧形不轮播加魅族效果

            <FrameLayout
                       android:layout_width="match_parent"
                       android:layout_height="200dp"
                       android:clipChildren="false">

                       <com.zhengsr.viewpagerlib.view.BannerViewPager
                           android:id="@+id/loop_viewpager_arc"
                           android:layout_width="match_parent"
                           android:layout_height="match_parent"
                           android:layout_marginLeft="20dp"
                           android:layout_marginRight="20dp"
                           android:clipChildren="false"
                           zsr:banner_isloop="false"
                           zsr:banner_switchtime="600" />

                       <com.zhengsr.viewpagerlib.indicator.ZoomIndicator
                           android:id="@+id/bottom_zoom_arc"
                           android:layout_width="match_parent"
                           android:layout_height="30dp"
                           android:layout_gravity="bottom|right"
                           android:layout_marginRight="20dp"
                           android:gravity="center"
                           zsr:zoom_alpha_min="0.5"
                           zsr:zoom_leftmargin="10dp"
                           zsr:zoom_max="1.5"
                           zsr:zoom_selector="@drawable/bottom_circle" />
                   </FrameLayout>

     放大版，弧形不轮播加魅族效果

     <FrameLayout
                 android:layout_width="match_parent"
                 android:layout_height="200dp"
                 android:clipChildren="false">

                 <com.zhengsr.viewpagerlib.view.BannerViewPager
                     android:id="@+id/loop_viewpager_arc"
                     android:layout_width="match_parent"
                     android:layout_height="match_parent"
                     android:layout_marginLeft="20dp"
                     android:layout_marginRight="20dp"
                     android:clipChildren="false"
                     zsr:banner_isloop="false"
                     zsr:banner_switchtime="600" />

                 <com.zhengsr.viewpagerlib.indicator.ZoomIndicator
                     android:id="@+id/bottom_zoom_arc"
                     android:layout_width="match_parent"
                     android:layout_height="30dp"
                     android:layout_gravity="bottom|right"
                     android:layout_marginRight="20dp"
                     android:gravity="center"
                     zsr:zoom_alpha_min="0.5"
                     zsr:zoom_leftmargin="10dp"
                     zsr:zoom_max="1.5"
                     zsr:zoom_selector="@drawable/bottom_circle" />
             </FrameLayout>

      移动版，不自动轮播

      <FrameLayout
                  android:layout_width="match_parent"
                  android:layout_height="200dp">

                  <com.zhengsr.viewpagerlib.view.BannerViewPager
                      android:id="@+id/loop_text2"
                      android:layout_width="match_parent"
                      android:layout_height="200dp"
                      zsr:banner_switchtime="600" />

                  <com.zhengsr.viewpagerlib.indicator.TransIndicator
                      android:id="@+id/bottom_trans_layout"
                      android:layout_width="wrap_content"
                      android:layout_height="30dp"
                      android:layout_gravity="bottom|right"
                      android:layout_marginRight="10dp"
                      zsr:trans_defaultcolor="@color/gray_cccc"
                      zsr:trans_leftmargin="10dp"
                      zsr:trans_movecolor="@color/white"
                      zsr:trans_type="round"
                      zsr:trans_round_radius="4dp"
                      zsr:trans_width="15dp"
                      zsr:trans_height="5dp"/>
              </FrameLayout>


      代码实现：

       private static final String[] RESURL = {
                  "http://img.mukewang.com/54bf7e1f000109c506000338-590-330.jpg",
                  "http://upload.techweb.com.cn/2015/0114/1421211858103.jpg",
                  "http://img1.cache.netease.com/catchpic/A/A0/A0153E1AEDA115EAE7061A0C7EBB69D2.jpg",
                  "http://image.tianjimedia.com/uploadImages/2015/202/27/57RF8ZHG8A4T_5020a2a4697650b89" +
                          "c394237ba9ffbb45fe8555a2cbec-6O6nmI_fw658.jpg"};

          private static final String[] TEXT = {"图像处理","LSB开发","游戏开发","梦想"};

       // 第一个viewpager

              mBannerCountViewPager = (BannerViewPager) findViewById(R.id.loop_viewpager);
              ZoomIndicator zoomIndicator = (ZoomIndicator) findViewById(R.id.bottom_scale_layout);


              //配置数据
              List<LoopBean> loopBeens = new ArrayList<>();
              for (int i = 0; i < TEXT.length; i++) {
                 LoopBean bean = new LoopBean();
                  bean.url = RESURL[i];
                  bean.text = TEXT[i];
                  loopBeens.add(bean);

              }
              //配置pagerbean，这里主要是为了viewpager的指示器的作用，注意记得写上泛型
              PageBean bean = new PageBean.Builder<LoopBean>()
                      .setDataObjects(loopBeens)
                      .setIndicator(zoomIndicator)
                      .builder();
              // 设置viewpager的动画，这里提供了三种，分别是MzTransformer，ZoomOutPageTransformer,
              // 和DepthPageTransformer，可以体验一下
              mBannerCountViewPager.setPageTransformer(false,new MzTransformer());
              //
              mBannerCountViewPager.setPageListener(bean, R.layout.loop_layout, new PageHelperListener() {
                  @Override
                  public void getItemView(View view, Object data) {
                      ImageView imageView = (ImageView) view.findViewById(R.id.loop_icon);
                      LoopBean bean = (LoopBean) data;
                      new GlideManager.Builder()
                              .setContext(LoopActivity.this)
                              .setImgSource(bean.url)
                              .setLoadingBitmap(R.mipmap.ic_launcher)
                              .setImageView(imageView)
                              .builder();
                      TextView textView = (TextView) view.findViewById(R.id.loop_text);
                      textView.setText(bean.text);

                      //如若你要设置点击事件，也可以直接通过这个view 来设置，或者图片的更新等等
                  }
              });

               //第二个轮播图

                      BannerViewPager arcBannerViewPager = (BannerViewPager) findViewById(R.id.loop_viewpager_arc);
                      ZoomIndicator arcZoomIndicator = (ZoomIndicator) findViewById(R.id.bottom_zoom_arc);
                      arcBannerViewPager.setPageTransformer(false,new MzTransformer());
                      PageBean arcbean = new PageBean.Builder<LoopBean>()
                              .setDataObjects(loopBeens)
                              .setIndicator(arcZoomIndicator)
                              .builder();
                      arcBannerViewPager.setPageListener(arcbean, R.layout.arc_loop_layout, new PageHelperListener() {
                          @Override
                          public void getItemView(View view, Object data) {
                              ArcImageView imageView = (ArcImageView) view.findViewById(R.id.arc_icon);
                              LoopBean bean = (LoopBean) data;
                              new GlideManager.Builder()
                                      .setContext(LoopActivity.this)
                                      .setImgSource(bean.url)
                                      .setLoadingBitmap(R.mipmap.ic_launcher)
                                      .setImageView(imageView)
                                      .builder();
                          }
                      });


                      //第三个轮播图

                      //配置数据
                      loopBeens = new ArrayList<>();
                      for (int i = 0; i < TEXT.length; i++) {
                          LoopBean bean2 = new LoopBean();
                          bean2.res = RES[i];
                          bean2.text = TEXT[i];
                          loopBeens.add(bean2);

                      }
                      BannerViewPager transBannerViewPager = (BannerViewPager) findViewById(R.id.loop_text2);
                      TransIndicator transIndicator = (TransIndicator) findViewById(R.id.bottom_trans_layout);
                      //配置pagerbean，这里主要是为了viewpager的指示器的作用，注意记得写上泛型
                      bean = new PageBean.Builder<LoopBean>()
                              .setDataObjects(loopBeens)
                              .setIndicator(transIndicator)
                              .builder();

                      transBannerViewPager.setPageListener(bean, R.layout.loop_layout, new PageHelperListener() {
                          @Override
                          public void getItemView(View view, Object data) {
                              ImageView imageView = (ImageView) view.findViewById(R.id.loop_icon);
                              final LoopBean bean = (LoopBean) data;
                              new GlideManager.Builder()
                                      .setContext(LoopActivity.this)
                                      .setImgSource(bean.res)
                                      .setLoadingBitmap(R.mipmap.ic_launcher)
                                      .setImageView(imageView)
                                      .builder();
                              TextView textView = (TextView) view.findViewById(R.id.loop_text);
                              textView.setText(bean.text);
                              view.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view) {
                                      Toast.makeText(LoopActivity.this, bean.text, Toast.LENGTH_SHORT).show();
                                  }
                              });

                              //如若你要设置点击事件，也可以直接通过这个view 来设置，或者图片的更新等等
                          }
                      });

                      //第四个轮播图

                      BannerViewPager textBannerViewPager = (BannerViewPager) findViewById(R.id.loop_viewpager_text);
                      TextIndicator textIndicator = (TextIndicator) findViewById(R.id.bottom_text_layout);


                      bean = new PageBean.Builder<LoopBean>()
                              .setDataObjects(loopBeens)
                              .setIndicator(textIndicator)
                              .builder();

                      textBannerViewPager.setPageListener(bean, R.layout.image_layout, new PageHelperListener() {
                          @Override
                          public void getItemView(View view, Object data) {
                              ImageView imageView = (ImageView) view.findViewById(R.id.icon);
                              LoopBean bean = (LoopBean) data;
                              new GlideManager.Builder()
                                      .setContext(LoopActivity.this)
                                      .setImgSource(bean.res)
                                      .setLoadingBitmap(R.mipmap.ic_launcher)
                                      .setImageView(imageView)
                                      .builder();


                              //如若你要设置点击事件，也可以直接通过这个view 来设置，或者图片的更新等等
                          }
                      });

      @Override
         protected void onPause() {
             super.onPause();
             mBannerCountViewPager.onPause();


         }

         @Override
         protected void onResume() {
             super.onResume();
             mBannerCountViewPager.onReusme();
         }

     指示器：

     三角形指示器
                 <com.zhengsr.viewpagerlib.indicator.TabIndicator
                        android:id="@+id/line_indicator"
                        android:layout_width="wrap_content"
                        android:layout_height="50dp"
                        android:background="@color/black_ff"
                        app:tab_color="@color/white"
                        app:tab_width="25dp"
                        app:tab_height="5dp"
                        app:tab_text_default_color="@color/white_ff"
                        app:tab_text_change_color="@color/white"
                        app:tab_show="true"
                        app:tab_text_type="normaltext"
                        app:tab_textsize="16sp"
                        app:visiabel_size="3"
                        app:tap_type="tri"
                        >
                    </com.zhengsr.viewpagerlib.indicator.TabIndicator>

                代码实现

                    <android.support.v4.view.ViewPager
                        android:id="@+id/viewpager"
                        android:layout_width="match_parent"
                        android:background="@color/gray_cccc"
                        android:layout_height="wrap_content"/>

                           final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
                                viewPager.setAdapter(new CusAdapter(getSupportFragmentManager()));
                                /**
                                 * 把 TabIndicator 跟viewpager关联起来
                                 */
                                TabIndicator tabIndecator = (TabIndicator) findViewById(R.id.line_indicator);
                                tabIndecator.setViewPagerSwitchSpeed(viewPager,600);
                                tabIndecator.setTabData(viewPager,mTitle, new TabIndicator.TabClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //顶部点击的方法公布出来
                                        viewPager.setCurrentItem(position);
                                    }
                                });


                            }


                            class CusAdapter extends FragmentPagerAdapter{

                                public CusAdapter(FragmentManager fm) {
                                    super(fm);
                                }

                                @Override
                                public Fragment getItem(int position) {
                                    return mFragments.get(position);
                                }

                                @Override
                                public int getCount() {
                                    return mFragments.size();
                                }
                            }
     条状指示器
      <com.zhengsr.viewpagerlib.indicator.TabIndicator
             android:id="@+id/line_indicator"
             android:layout_gravity="center_horizontal"
             android:layout_width="match_parent"
             android:layout_height="50dp"
             android:background="@color/gray_cccc"
             app:tab_color="@color/colorAccent"
             app:tab_width="50dp"
             app:tab_height="2dp"
             app:tab_text_default_color="@color/black_ff"
             app:tab_text_change_color="@color/colorAccent"
             app:tab_show="true"
             app:tab_text_type="normaltext"
             app:tab_textsize="16sp"
             app:visiabel_size="3"
             app:tap_type="rect"
             >

         </com.zhengsr.viewpagerlib.indicator.TabIndicator>

         <android.support.v4.view.ViewPager
             android:id="@+id/viewpager"
             android:layout_width="match_parent"
             android:background="@color/gray_cccc"
             android:layout_height="wrap_content"/>

               final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
                     TabIndicator tritabIndecator = (TabIndicator) findViewById(R.id.line_indicator);
                     viewPager.setAdapter(new CusAdapter(getSupportFragmentManager()));
                     tritabIndecator.setViewPagerSwitchSpeed(viewPager,600);
                     // 使用这个方法，则使用xml里面的控件
                     //tritabIndecator.setTabData(viewPager,new TabIndicator.TabClickListener()
                     tritabIndecator.setTabData(viewPager, mTitle,new TabIndicator.TabClickListener() {
                         @Override
                         public void onClick(int position) {
                             viewPager.setCurrentItem(position);
                         }
                     });


                 }


                 class CusAdapter extends FragmentPagerAdapter{

                     public CusAdapter(FragmentManager fm) {
                         super(fm);
                     }

                     @Override
                     public Fragment getItem(int position) {
                         return mFragments.get(position);
                     }

                     @Override
                     public int getCount() {
                         return mFragments.size();
                     }
                 }

     其他指示器得使用

 <it.neokree.materialtabs.MaterialTabHost
        android:id="@+id/tabHost"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:layout_below="@+id/toolbar"
        app:primaryColor="#009688"
        app:accentColor="#ffc400" />

    <android.support.v4.view.ViewPager
        android:id="@+id/pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@+id/tabHost" />


    代码实现

  tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
        pager = (ViewPager) this.findViewById(R.id.pager );

        // init view pager
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);

            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this)
            );

        }

    }


    @Override
    public void onTabSelected(MaterialTab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        public Fragment getItem(int num) {
            return new FragmentText();
        }

        @Override
        public int getCount() {
            return 16;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Section " + position;
        }
    }

   设置指示器 icon实现：
      for (int i = 0; i < pagerAdapter.getCount(); i++) {
                tabHost.addTab(
                        tabHost.newTab()
                                .setIcon(getIcon(i))
                                .setTabListener(this)
                );
            }

## 五、沉浸式状态栏的使用
    依赖compile 'org.zackratos:ultimatebar:1.0.3'

    1.自定义颜色的状态栏和导航栏
    在 onCreate() 方法中：
    UltimateBar.newColorBuilder()
            .statusColor(statusColor)       // 状态栏颜色
            .statusDepth(50)                // 状态栏颜色深度
            .applyNav(true)                 // 是否应用到导航栏
            .navColor(navColor)             // 导航栏颜色
            .navDepth(50)                   // 导航栏颜色深度
            .build(this)
            .apply();

如果不需要设置颜色深度：

UltimateBar.newColorBuilder()
        .statusColor(statusColor)   // 状态栏颜色
        .applyNav(true)             // 是否应用到导航栏
        .navColor(navColor)         // 导航栏颜色
        .build(this)
        .apply();
2.半透明状态栏和导航栏

在 onCreate() 方法中：

UltimateBar.newTransparentBuilder()
        .statusColor(Color.BLUE)        // 状态栏颜色
        .statusAlpha(100)               // 状态栏透明度
        .applyNav(true)                 // 是否应用到导航栏
        .navColor(Color.GREEN)          // 导航栏颜色
        .navAlpha(100)                  // 导航栏透明度
        .build(this)
        .apply();

如果仅需要设置状态栏的半透明效果：

UltimateBar.newTransparentBuilder()
        .statusColor(Color.BLUE)        // 状态栏颜色
        .statusAlpha(100)               // 状态栏透明度
        .applyNav(false)                // 是否应用到导航栏
        .build(this)
        .apply();
3.沉浸式状态栏和导航栏：

在 onCreate() 方法中：



UltimateBar.newImmersionBuilder()
        .applyNav(true)         // 是否应用到导航栏
        .build(this)
        .apply();

4.隐藏状态栏和导航栏：

在 onWindowFocusChanged() 方法中：

@Override
public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    if (hasFocus) {
        UltimateBar.newHideBuilder()
                .applyNav(true)     // 是否应用到导航栏
                .build(this)
                .apply();
    }
}


5.在 DrawerLayout 中设置自定义颜色的状态栏和导航栏：

首先需要设置 DrawerLayout 下面的主局部中添加 android:fitsSystemWindows="true"：

<android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="false">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:fitsSystemWindows="true">

    </LinearLayout>

    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/SpringGreen"
        android:layout_gravity="left"
        android:fitsSystemWindows="false"/>

</android.support.v4.widget.DrawerLayout>

注意是 DrawerLayout 下面的主布局，DrawerLayout 本身和抽屉布局都不能添加。

然后在 onCreate() 方法中：

UltimateBar.newDrawerBuilder()
        .statusColor(color)     // 状态栏颜色
        .statusDepth(0)         // 状态栏颜色深度
        .applyNav(true)         // 是否应用到导航栏
        .navColor(color)        // 导航栏颜色
        .navDepth(0)            // 导航栏颜色深度
        .build(this)
        .apply();




## 六、下拉刷新

   正常得下拉刷新
  TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refresh);
        ProgressLayout headerView = new ProgressLayout(this);
        refreshLayout.setHeaderView(headerView);
        View exHeader = View.inflate(this, R.layout.header_music, null);
        refreshLayout.addFixedExHeader(exHeader);
        refreshLayout.setOverScrollRefreshShow(false);
//        refreshLayout.setFloatRefresh(true);
        adapter = new MusicAdapter();
        listView.setAdapter(adapter);
        adapter.refreshCard();

  上下弹动得下拉刷新
   final TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refresh);
  //        ProgressLayout headerView = new ProgressLayout(getContext());
          BezierLayout headerView = new BezierLayout(this);
          refreshLayout.setHeaderView(headerView);
  //        refreshLayout.setFloatRefresh(false);
          refreshLayout.setPureScrollModeOn();

   gridview刷新

      final TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refresh);
           SinaRefreshView headerView = new SinaRefreshView(this);
           headerView.setArrowResource(R.drawable.arrow);
           headerView.setTextColor(0xff745D5C);
   //        TextHeaderView headerView = (TextHeaderView) View.inflate(this,R.layout.header_tv,null);
           refreshLayout.setHeaderView(headerView);

           LoadingView loadingView = new LoadingView(this);
           refreshLayout.setBottomView(loadingView);

           adapter = new ScienceAdapter();
           gridView.setAdapter(adapter);
           adapter.refreshCard();

    photo刷新

      final TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refresh);
    //        ProgressLayout headerView = new ProgressLayout(this);
            BezierLayout headerView = new BezierLayout(this);
            refreshLayout.setHeaderView(headerView);
            refreshLayout.setMaxHeadHeight(140);
    //        refreshLayout.setFloatRefresh(true);
    //        refreshLayout.setPureScrollModeOn(true);
            refreshLayout.setOverScrollBottomShow(false);
    //        refreshLayout.setAutoLoadMore(true);

    //        addHeader();
            refreshCard();

      一般的刷新
        refreshLayout.setOverScrollRefreshShow(false);

        webview刷新
         TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refreshLayout);
                ProgressLayout header = new ProgressLayout(this);
                refreshLayout.setHeaderView(header);
                refreshLayout.setFloatRefresh(true);
                refreshLayout.setOverScrollRefreshShow(false);
                refreshLayout.setHeaderHeight(140);
                refreshLayout.setMaxHeadHeight(240);
                refreshLayout.setOverScrollHeight(200);
                refreshLayout.setEnableLoadmore(false);
                header.setColorSchemeResources(R.color.Blue, R.color.Orange, R.color.Yellow, R.color.Green);
        //        header.setColorSchemeColors(0xff4674e7,0xff0ba62c);

                mWebView = (WebView) findViewById(R.id.webView);
                mWebView.getSettings().setJavaScriptEnabled(true);
                mWebView.loadUrl("https://dribbble.com/shots");

                refreshLayout.startRefresh();

         layout刷新

             final TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refresh);
                 ProgressLayout header = new ProgressLayout(this);
                 refreshLayout.setHeaderView(header);
                 refreshLayout.setFloatRefresh(true);
                 refreshLayout.setEnableOverScroll(false);
                 refreshLayout.setHeaderHeight(140);
                 refreshLayout.setMaxHeadHeight(240);
                 refreshLayout.setTargetView(rv);

                 refreshCard();

          什么也不用设置就是上下弹动


          箭头刷新

           refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refresh);
                  SinaRefreshView headerView = new SinaRefreshView(this);
                  headerView.setArrowResource(R.drawable.arrow);
                  headerView.setTextColor(0xff745D5C);
          //        TextHeaderView headerView = (TextHeaderView) View.inflate(this,R.layout.header_tv,null);
                  refreshLayout.setHeaderView(headerView);

                  LoadingView loadingView = new LoadingView(this);
                  refreshLayout.setBottomView(loadingView);

                  adapter = new ScienceAdapter();
                  gridView.setAdapter(adapter);
                  adapter.refreshCard();

## 七、tabview使用：
   <com.TabView
        android:id="@+id/tabView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />


          List<TabViewChild> tabViewChildList = new ArrayList<>();
                TabViewChild tabViewChild01 = new TabViewChild(R.drawable.home_index_focused, R.drawable.home_index_normal, "首页", new FragmentMain());
                TabViewChild tabViewChild02 = new TabViewChild(R.drawable.home_quantify_focused, R.drawable.home_quantify_normal, "互动", new FragmentInteraction());
                TabViewChild tabViewChild03 = new TabViewChild(R.drawable.home_trade_focused, R.drawable.home_trade_normal, "推荐", new FragementWeek());
                TabViewChild tabViewChild04 = new TabViewChild(R.drawable.home_latestnews_focused, R.drawable.home_latestnews_normal, "学习", new FragmentStudy());
                TabViewChild tabViewChild05 = new TabViewChild(R.drawable.home_account_focused, R.drawable.home_account_normal, "我的", new FragmentAccount());
                tabViewChildList.add(tabViewChild01);
                tabViewChildList.add(tabViewChild02);
                tabViewChildList.add(tabViewChild03);
                tabViewChildList.add(tabViewChild04);
                tabViewChildList.add(tabViewChild05);

                TabView tabView = (TabView) findViewById(R.id.tabView);
                tabView.setTabViewChild(tabViewChildList, getSupportFragmentManager());
                tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
                    @Override
                    public void onTabChildClick(int position, ImageView currentImageIcon, TextView currentTextView) {
                        onTabView(position);
                        if (position == 4 || position == 2) {
                            setShowTitle(true);
                        } else {
                            setShowTitle(false);
                        }
                    }
                });
