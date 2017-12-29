package com.vip.abnerming.utils.net.gson;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * ClassName:MGsonResponseBodyConverter
 * Description TODO 服务器响应数据处理
 * created by BAI
 * Data 2016/12/20
 */
public class MGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    MGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String result = value.string();
            Log.e("json",result);
            return adapter.fromJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            value.close();
        }
    }
}
