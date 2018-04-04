
import java.io.File;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;



public class Main extends PApplet{


	static String pdfname= "output";

	public static void main(String[]args){
		play("pics");
	}

	static int times;
	static String folder;
	public static void play(String path){
		folder = path;

		File f = new File(folder);
		if(!f.exists())
			return;

		images = listFilesForFolder(f);

		times = images.size()/18+1;
		if(images.size()%18==0)
			times--;
		
		PApplet.main("Main");
	}


	public static ArrayList<String> listFilesForFolder(final File folder) {
		ArrayList<String> images = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {

			System.out.println(fileEntry.getName());
			if(fileEntry.getName().contains(format))
				images.add(fileEntry.getName());
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


	static String format = "png";
	static ArrayList<String>  images;

	public void settings() {
		size((int)mm(420),(int)mm(800)); 

	}

	private String[] getImages() {
		String[] cuImages = new String[18];

		for(int i =0; i<cuImages.length; i++){
			if(i<images.size()){
				cuImages[i] = images.get(i);
				images.remove(i);
			}
		}
		return cuImages;
	}


	static int count =0;
	public void draw(){

		if(count == times)
			exit();
		String[] pdfImages = getImages(); 
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
		//		pdfname +="-"+n;
		if(n==0)
			beginRecord(PDF, pdfname+".pdf");
		else
			beginRecord(PDF, pdfname+"-"+n+".pdf");

		PImage img;

		float x=0, y=0;
		int index=0;
		for(int i=0; i<3; i++){   
			x=0; y=0;
			for(int j=0; j<6; j++){

				if(index<pdfImages.length)
					img = loadImage(pdfImages[index]);
				else break;
				index++;

				x = (float) (42.5 + i*(95+25));
				y = (float) (52.5 + j*(95+25));

				//rect(mm(x), mm(y), mm(95), mm(95)); //row 2

				image(img,mm(x), mm(y), mm(95), mm(95));
			}
		}

		endRecord();

	}


	static final float MM_TO_PIXEL_RATIO = 0.3527778f;

	public float mm(float wantedMM) {
		return wantedMM / MM_TO_PIXEL_RATIO;
	}

}
