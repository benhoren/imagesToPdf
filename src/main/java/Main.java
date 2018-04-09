
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;



public class Main extends PApplet{


	static String pdfname= "output";
	static String text= "";
	static String fontName;
	static float textSize;
	static float distance;

	//	public static void main(String[]args){
	//		String text ="text on pics";
	//		play("pics", text, "Arial", (float)20, 10);
	//	}

	static int times;
	static String folder;
	public static void play(String path, String itext, String ifont, float itextSize, float idistance){
		folder = path;
		textSize= itextSize;
		fontName = ifont;
		distance = idistance;

		text = fixText(itext);
		System.out.println(text);

		if(text == null) text = "";
		if(fontName == null || fontName.isEmpty())
			fontName = "Arial";

		if(!text.isEmpty() && distance<0)
			distance = 0;

		if(!text.isEmpty() && textSize<=0)
			textSize = 15;

		File f = new File(folder);
		if(!f.exists())
			return;

		images = listFilesForFolder(f);


		times = images.size()/18+1;
		if(images.size()%18==0)
			times--;



		System.out.println("pics: "+images.size()+" rounds: "+times);
		System.out.println(text);
		System.out.println("size: "+textSize);
		System.out.println("font: "+fontName);
		System.out.println("distance: "+distance);


		PApplet.main("Main");
	}


	public static ArrayList<String> listFilesForFolder(final File folder) {
		ArrayList<String> images = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {

			for(int i=0; i<format.length; i++){
				//				String ext2 = FilenameUtils.getExtension("bar.exe");
				if(fileEntry.getName().toUpperCase().contains(format[i])){
					images.add(fileEntry.getName());
					break;
				}

			}
		}
		return images;
	}




	public static void sleep(int mil){
		try {
			Thread.currentThread();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	static final String[] format ={ "PNG"	, "JPG", "JPEG", "GIF"};
	static ArrayList<String>  images;

	private String[] getImages() {
		String[] cuImages = new String[18];

		for(int i =0; i<cuImages.length; i++){
			if(0<images.size()){
				cuImages[i] = images.get(0);
				images.remove(0);
			}
		}
		return cuImages;
	}

	public static String[] fontList(){
		return PFont.list();
	}

	public void setup(){
		//		size((int)mm(420),(int)mm(800)); 

		String[] fontList = PFont.list();
		//		System.out.println(Arrays.toString(fontList));

		if(textSize>0)
			myFont = createFont(fontName, textSize);

	}
	PFont myFont;
	public void settings() {
		size((int)mm(420),(int)mm(800)); 

		//		String[] fontList = PFont.list();
		//		System.out.println(Arrays.toString(fontList));
		//		
		//		PFont myFont = createFont(fontName, textSize);
		//		textAlign(CENTER);
		//		 textFont(myFont);
	}


	static int count =0;
	public void draw(){

		if(count ==  times-1){
			//			sleep(7000);
			exit();
		}

		String[] pdfImages = getImages(); 
		if(pdfImages == null)
			exit();
		count ++;

		System.out.println("round");
		File f = new File(pdfname+".pdf");
		boolean ok = false;
		if(!f.exists())
			ok = true;
		int n=0;
		while(!ok){
			n++;
			f = new File(pdfname+"-"+n+".pdf");
			if(!f.exists())
				ok = true;
		}

		if(n==0)
			beginRecord(PDF, pdfname+".pdf");
		else
			beginRecord(PDF, pdfname+"-"+n+".pdf");

		PImage img;

		float x=0, y=0;
		int index=0;

		for(int j=0; j<6; j++){
			x=0; y=0;
			for(int i=0; i<3; i++){   

				if(index<pdfImages.length && pdfImages[index]!=null && !pdfImages[index].isEmpty())
					img = loadImage(folder+"//"+pdfImages[index]);
				else break;
				index++;

				x = (float) (42.5 + i*(95+25));
				y = (float) (52.5 + j*(95+25));


				image(img,mm(x), mm(y), mm(95), mm(95));

				fill(255);


				//				 textSize(textSize);
				if(textSize>0){
					fill(0);
					textAlign(CENTER, BOTTOM);
					textFont(myFont);
					text(text, mm(x+(float) 47.5), mm(y+ (95-distance)));
				}
			}
		}

		float disx = (float) 21.25;
		float disy = (float) 26.25;

		ellipse(mm(disx),mm(disy) ,mm(2),mm(2)); 
		ellipse(mm(420-disx),mm(disy) ,mm(2),mm(2)); 
		ellipse(mm(disx),mm(800-disy) ,mm(2),mm(2)); 
		ellipse(mm(420-disx),mm(800-disy) ,mm(2),mm(2)); 

		//rect(30, 20, 55, 55);
		fill(255,0,0);
		noStroke();
		rect(0, 0, mm(10), mm(10));
		rect(mm(410), 0, mm(10), mm(10));
		rect(0, mm(790), mm(10), mm(10));
		rect(mm(410), mm(790), mm(10), mm(10));


		endRecord();

	}


	static final float MM_TO_PIXEL_RATIO = 0.3527778f;

	public float mm(float wantedMM) {
		return wantedMM / MM_TO_PIXEL_RATIO;
	}

	public static String fixText(String text){
		System.out.println(text);
		String itext ="";
		String[] words = text.split(" ");

		String[] strings = new String[words.length];

		System.out.println(Arrays.toString(words));

		for(int i=0; i<strings.length; i++)strings[i]="";

		boolean ok = false; int index=0;
		for(int i=0; i<words.length; i++){

			if(isHebrew(words[i])){
				strings[index] += words[i]+" ";
				ok = true;
			}
			else{
				if(ok) index++;
				strings[index] = words[i]+" ";
				index++;
				ok = false;
			}
		}


		System.out.println(Arrays.toString(strings));

		for(int i=0; i<strings.length; i++){
			if(isHebrew(strings[i]))
				strings[i] = new String(reverse(strings[i].toCharArray()));
		}

		for(int i=0; i<strings.length; i++){
			itext+=strings[i]+" ";
		}

		return itext;
	}

	/**
	 * this function check is the String is in hebrew by search for hebrew letters in it.
	 * @param s 
	 * @return true if contains hebrew false otherwise
	 */
	public static boolean isHebrew(String s){
		if(s==null) return false;

		return     s.contains("à")
				|| s.contains("á")
				|| s.contains("â")
				|| s.contains("ã")
				|| s.contains("ä")
				|| s.contains("å")
				|| s.contains("æ")
				|| s.contains("ç")
				|| s.contains("è")
				|| s.contains("é")
				|| s.contains("ë")
				|| s.contains("ê")
				|| s.contains("ì")
				|| s.contains("î")
				|| s.contains("í")
				|| s.contains("ð")
				|| s.contains("ï")
				|| s.contains("ñ")
				|| s.contains("ò")
				|| s.contains("ô")
				|| s.contains("ó")
				|| s.contains("ö")
				|| s.contains("õ")
				|| s.contains("÷")
				|| s.contains("ø")
				|| s.contains("ù")
				|| s.contains("ú");
	}
}
