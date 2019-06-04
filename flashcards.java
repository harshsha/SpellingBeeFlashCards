import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class Dict {
static Scanner stdin = new Scanner(System.in);
public static void main(String[] args) throws Exception {
// TODO Auto-generated method stub
System.out.println("Insert list");
ArrayList<String> search = new ArrayList();
// String search = "";
String ds = "";

while (true) {
ds = stdin.nextLine();
if (ds.trim().equals("-1")) {
break;
}
ds.replaceAll(".", "");
ds = ds.replaceAll("[^a-z]+","");
search.add(ds);
//System.out.println("added "+ds);
}
System.out.println("Done");
for(int i = 0; i < search.size(); i++){
System.out.println();
System.out.println();

searchWord(search.get(i));

System.out.println();
System.out.println();
}

}

public static void searchWord(String word) throws Exception{
searchDef(word);
searchSyn(word);
}

public static void searchDef(String word) throws Exception{
String ua = "http://www.dictionaryapi.com/api/v1/references/collegiate/xml/"+word+"?key=3046d0b3-05d9-4ba8-b38f-2dd382ac1af1";
URL a = new URL(ua);
URLConnection b = a.openConnection();
BufferedReader c = new BufferedReader(new InputStreamReader(a.openStream()));
String result = "";
String s = null;
while ((s=c.readLine())!=null)
{
result+=s;
}

System.out.println(word);
search(result, "<vr>", "</vr>", "Varient Spelling - ");
search(result, "<et>", "</et>", "Etymology - ");
search(result, "<dt>", "</dt>", "Definition - ");
search(result, "<fl>", "</fl>", "Functional Label (part of speech) - ");
c.close();

}

public static void searchSyn(String word) throws Exception{
//System.out.println("called for - "+word);
String ua = "http://www.dictionaryapi.com/api/v1/references/thesaurus/xml/"+word+"?key=e331a9e4-4c06-4fb7-b649-c685ee1fc26d";
URL a = new URL(ua);
URLConnection b = a.openConnection();
BufferedReader c = new BufferedReader(new InputStreamReader(a.openStream()));
String result = "";
String s = null;
while ((s=c.readLine())!=null)
{
result+=s;

}

search(result, "<syn>", "</syn>", "Synonyms - ");
search(result, "<ant>", "</ant>", "Antynoms - ");
search(result, "<near>", "</near>", "Near Antynoms - ");
search(result, "<vi>", "</vi>", "Example Sentence - ");
c.close();

}

public static void search(String result, String s1, String s2, String rp){
try{
String a = new String(rp +result.substring(result.indexOf(s1)+s1.length(), result.indexOf(s2)));
int b;
int c;
//System.out.println("ORIGINAL"+a);
while(a.contains("<") && a.contains(">")){
b=a.indexOf("<");
c=a.indexOf(">")+1;
a=a.substring(0, b) +""+ a.substring(c,a.length());
//System.out.println(a);
}
System.out.println(a);
}catch(Exception e){

}finally{

}
}
}