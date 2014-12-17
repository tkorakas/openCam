package opencv;

import java.io.File;
import org.bytedeco.*;
import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_highgui;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.FrameRecorder.Exception;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.OpenCVFrameRecorder;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_calib3d.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_highgui.CvCapture;


public class Picture {
	private static int counter = 0;
	public Picture(){
		Thread t = new Thread(){
			public void run(){
				 OpenCVFrameGrabber grabber=new OpenCVFrameGrabber(CV_CAP_ANY);
				 boolean filex = fileExist("Capture.jpeg");
				 	String filename = "Capture.jpeg";
					String file = "Capture";
					String type = "jpeg";
					while (!filex) {
						String newfilename = file + counter + "." + type;
						filename = newfilename;
						System.out.println(filename);
						filex = fileExist(filename);
					}
			        try
			        {
			            grabber.start();
			            IplImage img=grabber.grab();
			            if(img!=null)
			            {
			                cvSaveImage(filename, img);
			                grabber.stop();
			            }
			        }
			        catch(org.bytedeco.javacv.FrameGrabber.Exception ae)
			        {
			            ae.printStackTrace();

			        }
			}
		};
		t.start();
	}

	boolean fileExist(String name) {
		File test = new File(name);
		if (test.exists()) {
			counter++;
			return false;

		}
		return true;
	}
}
