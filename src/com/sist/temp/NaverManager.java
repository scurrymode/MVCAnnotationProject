package com.sist.temp;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NaverManager {
	NaverDAO dao = new NaverDAO();
	public static void main(String[] args){
		NaverManager nm= new NaverManager();
//		List<String> list = nm.getMovieCodeData();
//		int i=0;
//		for(String code:list){
//			System.out.println(i+":"+code);
//			i++;
//		}
		nm.getMovieInfoData();
		
	}
	
	// code번호 모아오기
	public List<String> getMovieCodeData() {
		List<String> list = new ArrayList<String>();
		try {
			/*
			 * <td class="title">
						<div class="tit5">
							<a href="/movie/bi/mi/basic.nhn?code=89615" title="초한지 - 천하대전">초한지 - 천하대전</a>
						</div>
					</td>
			 */
			for(int i=1; i<=12; i++){//총 12페이지까지 네이버 영화레코드
				Document doc = Jsoup.connect("http://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=pnt&date=20170531&page="+i).get();
				Elements aelem=doc.select("td.title div.tit5 a");
				for(int j=0; j<aelem.size();j++){
					Element elem= aelem.get(j);
					String href=elem.attr("href");
					String code= href.substring(href.lastIndexOf("=")+1);
					list.add(code);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return list;
	}
	// data 읽기
	public List<NaverMovieVO> getMovieInfoData(){
		List<NaverMovieVO> list= new ArrayList<NaverMovieVO>();
		try{
			List<String> cList = getMovieCodeData();
			for(int i=0; i<cList.size();i++){
				if(i==166||i==210||i==213||i==245||i==394||i==430||i==474||i==539||i==550||i==559||i==582)continue;
				String code=cList.get(i);
				Document doc = Jsoup.connect("http://movie.naver.com/movie/bi/mi/basic.nhn?code="+code).get();
				Element title=doc.select("div.mv_info h3.h_movie a").first();
				Element poster=doc.select("div.poster img").first();
				Element director=doc.select("div.info_spec2 dl.step1 dd a").first();
				Element actor=doc.select("div.info_spec2 dl.step2 dd a").first();
				Element story=doc.select("div.story_area p.con_tx").first();
				Element genre = doc.select("#content > div > div.mv_info_area > div.mv_info > dl > dd:nth-child(2) > p > span:nth-child(1) > a").first();
				Element regdate = doc.select("#content > div > div.mv_info_area > div.mv_info > dl > dd:nth-child(2) > p > span:nth-child(4) > a:nth-child(1)").first();
				Element grade = doc.select("#content > div > div.mv_info_area > div.mv_info > dl > dd:nth-child(8) > p > a:nth-child(1)").first();
				System.out.println(i+":"+title.text()+" "+director.text()+" "+regdate.text()+" "+grade.text()+" "+genre.text()+" "+actor.text()+" "+poster.attr("src"));
				NaverMovieVO vo = new NaverMovieVO();
				vo.setCode(Integer.parseInt(code));
				vo.setTitle(title.text());
				vo.setPoster(poster.attr("src"));
				vo.setDirector(director.text());
				vo.setActor(actor.text());
				String str = story.text();
				str=str.replace("'", "");
				str=str.replace("\"", "");
				vo.setStory(str);
				dao.movieInsert(vo);
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return list;
	}
}
