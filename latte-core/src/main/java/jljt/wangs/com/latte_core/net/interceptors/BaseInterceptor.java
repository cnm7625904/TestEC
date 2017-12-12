package jljt.wangs.com.latte_core.net.interceptors;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/12/12.
 * GET从url获取参数
 * POST从请求体responsebody获取参数
 * linkedhashmap有序hashmap无序
 * 学习函数
 */

public abstract class BaseInterceptor implements Interceptor{
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        return null;
//    }
    protected LinkedHashMap<String,String> getUrlparameters(Chain chain){
        HttpUrl url=chain.request().url();
        int size=url.querySize();
        final LinkedHashMap<String,String> params=new LinkedHashMap<>();
        for(int i=0;i<size;i++){
            params.put(url.queryParameterName(i),url.queryParameterValue(i));
        }
        return  params;
    }
    protected  String getUrlparameters(Chain chain,String key){
        final Request request=chain.request();
        return request.url().queryParameter(key);
    }
    protected LinkedHashMap<String,String> getBodyparameters(Chain chain){
        final FormBody formBody= (FormBody) chain.request().body();
        final LinkedHashMap<String,String> params=new LinkedHashMap<>();
        int size=formBody.size();
        for (int i=0;i<size;i++){
            params.put(formBody.name(i),formBody.value(i));
        }
        return  params;
    }
    protected String  getBodyparameters(Chain chain,String key){
         return  getBodyparameters(chain).get(key);
    }
}
