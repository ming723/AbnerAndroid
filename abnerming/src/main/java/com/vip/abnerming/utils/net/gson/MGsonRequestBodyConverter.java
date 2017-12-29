package com.vip.abnerming.utils.net.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * ClassName:MGsonRequestBodyConverter
 * Description TODO 服务器请求数据处理
 * created by BAI
 * Data 2016/12/20
 */
public class MGsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final TypeAdapter<T> adapter;

    private  boolean serializeNulls;
    private  boolean generateNonExecutableJson;
    private  boolean prettyPrinting;
    private  final String JSON_NON_EXECUTABLE_PREFIX = ")]}'\n";

    MGsonRequestBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = newJsonWriter(writer);
        adapter.write(jsonWriter, value);
        jsonWriter.close();
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }

    /**
     * Returns a new JSON writer configured for the settings on this Gson instance.
     */
    public JsonWriter newJsonWriter(Writer writer) throws IOException {
        if (generateNonExecutableJson) {
            writer.write(JSON_NON_EXECUTABLE_PREFIX);
        }
        JsonWriter jsonWriter = new JsonWriter(writer);
        if (prettyPrinting) {
            jsonWriter.setIndent("  ");
        }
        jsonWriter.setSerializeNulls(serializeNulls);
        return jsonWriter;
    }
}
