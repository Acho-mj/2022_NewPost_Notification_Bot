package dicobot;


import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class bot implements EventListener{ 
	public static JDA jda;
	public static void main(String[] args)throws LoginException, IOException {
		 JDA jda = JDABuilder.createDefault("Token").build();
		      jda.addEventListener(new bot());
		   
		      
		    String url = "https://www.swu.ac.kr/front/boardlist.do?bbsConfigFK=4";
			Document webhtml = Jsoup.connect(url).get();
			Elements title = webhtml.select("td.title>div>a>div>span");
			Element strT1 = title.get(0);
			String oldtitle = strT1.text();  //게시글 제목
				
		      
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
		public void run() {
			try {
				
				String url = "https://www.swu.ac.kr/front/boardlist.do?bbsConfigFK=4";
				Document webhtml = Jsoup.connect(url).get();
				Elements title = webhtml.select("td.title>div>a>div>span");
				Element strT1 = title.get(0);
				String newtitle = strT1.text();  //게시글 제목
				
				
				if(!oldtitle.equals(newtitle)) {
					System.out.println("공지사항 최신 글 제목 : " + newtitle);
					
					for(int i = 1; i <= 5; i++) {
						Element strT2 = title.get(i);
						String severalT = strT2.text();
						System.out.println( ""+i+"번째 " + "게시글 제목 : " + severalT);
					}
					newtitle = oldtitle;}
					
			}catch(IOException e) {
			}finally {
			}
		}			
		};
		timer.schedule(task, 1000, 60000);
	}
	
	@Override
	public void onEvent(GenericEvent event) {
		if (event instanceof ReadyEvent)
		        System.out.println("MyBot is ready!");
	}
	
	public void onMessageReceived(MessageReceivedEvent event) {
	     if (event.getMessage().getContentRaw().equals("hi")) {
	        event.getChannel().sendMessage("hihi").queue();
	        }
	     }

}