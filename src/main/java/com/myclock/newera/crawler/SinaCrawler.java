package com.myclock.newera.crawler;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.myclock.newera.bean.StockSina;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SinaCrawler extends BaseCrawler {
    @Override
    String setHost() {
        return "stock2.finance.sina.com.cn";
    }
    @Override
    Cookie setCookie() {return null;}

    public List<StockSina> crawlerStockData() throws Exception {
        WebClient webClient = init(new WebClient());
        String url = "http://stock2.finance.sina.com.cn/futures/api/json.php/IndexService.getInnerFuturesMiniKLine%sm?symbol=%s";
        String[] symbols = {"HC0", "RB0", "SR0"};
        String[] types = {"5", "15"};
        List<StockSina> allList=new ArrayList<>();
        for (String type : types) {
            for (String symbol : symbols) {
                try {
                    String content = webClient.getPage(String.format(url,type,symbol)).getWebResponse().getContentAsString("UTF-8");
                    List<StockSina> list = getData(content, symbol,type);
                    if (list.size() > 0) allList.addAll(list);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return allList;
    }

    private List<StockSina> getData(String content,String symbol,String type) {
        List<StockSina> list=new ArrayList<>();
        if(StringUtils.isBlank(content)||!content.startsWith(""))return list;
        String[] strs=content.split("\\],\\[");
        int num=0;
        for (String str:strs){
            str=str.replaceAll("[\\[\"\\]]","");
            String[] sts=str.split(",");
            if(sts.length!=6) continue;
            StockSina sina=new StockSina();
            sina.setDatetime(sts[0].replace(",",""));
            sina.setOpen(sts[1].replaceAll(",",""));
            sina.setHigh(sts[2].replace(",",""));
            sina.setLow(sts[3].replace(",",""));
            sina.setClose(sts[4].replace(",",""));
            sina.setVolume(sts[5].replace(",",""));
            sina.setSymbol(symbol);
            sina.setType(type);
            list.add(sina);
            num++;
            if (num>15) break;
        }
        return list;
    }

    public static void main(String[] args){
        try {
//            new SinaCrawler().crawlerStockData();
            String url = "http://stock2.finance.sina.com.cn/futures/api/json.php/IndexService.getInnerFuturesMiniKLine%sm?symbol=%s";
            System.out.println(String.format(url,"5","uuuu"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
