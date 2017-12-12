package jljt.wangs.com.latte_core.net;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import jljt.wangs.com.latte_core.app.ConfigKeys;
import jljt.wangs.com.latte_core.app.Latte;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/12/8.
 * 类部类Holder
 */

public class RestCreator {

    //设置全局变量
    public static final class ParamsHolder{
        public static final WeakHashMap<String,Object> PARAMS=new WeakHashMap<>();
    }
    public static final WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RetrofitHolder{
        /**
         * 获取URL
         */
        private static final String BASE_URL= Latte.getConfigurations(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    /**
     * okhttp3
     */
    private static final class OKHttpHolder{
        private static final int TIME_OUT=60;
        private static final OkHttpClient.Builder BUILDER=new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS=Latte.getConfigurations(ConfigKeys.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor(){
            if(INTERCEPTORS!=null&&!INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor:INTERCEPTORS) {
                      BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }
        private static final OkHttpClient OK_HTTP_CLIENT=addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

//        private static final OkHttpClient OK_HTTP_CLIENT=new OkHttpClient.Builder()
//                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
//                .build();
    }
    /**
     * 服务
     */
    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE=RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
