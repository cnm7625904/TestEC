package jljt.wangs.com.latte_core.net.interceptors;

import android.support.annotation.RawRes;

import java.io.IOException;

import jljt.wangs.com.latte_core.util.file.FileUtil;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/12/12.
 * 构造方法实例
 */

public class DebugInterceptor extends BaseInterceptor {
    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;
    //用构造方法去初始化

    public DebugInterceptor(String debugUrl, int debugRawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = debugRawId;
    }
    //获取文件
    private Response getResponse(Chain chain,String json){
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();

    }
    private Response debugRessponse(Chain chain, @RawRes int rawId){
        final String json= FileUtil.getRawFile(rawId);
        return getResponse(chain,json);
    }
    /**
     * 拦截器
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {

        final String url = chain.request().url().toString();
        if (url.contains(DEBUG_URL)) {
            return debugRessponse(chain, DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());

    }
}
