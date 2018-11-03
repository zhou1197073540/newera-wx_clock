package com.myclock.newera.crawler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.apache.commons.lang3.StringUtils;

abstract class BaseCrawler {
    abstract String setHost();
    abstract Cookie setCookie();

    WebClient init(WebClient webClient) throws Exception {
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setRedirectEnabled(true);
        webClient.setJavaScriptTimeout(10000);
        if(StringUtils.isNotBlank(setHost())) webClient.addRequestHeader("Host", setHost());
        if(null!=setCookie()) webClient.getCookieManager().addCookie(setCookie());
//        webClient.getCookieManager().addCookie(new Cookie("xueqiu.com", "Cookie", ""));
        webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);
        return webClient;
    }
}
