package opencv;

import org.bytedeco.*;
import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_highgui;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_highgui.CvCapture;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.FrameRecorder.Exception;
import org.bytedeco.javacv.OpenCVFrameRecorder;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_calib3d.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;
import static org.bytedeco.javacpp.opencv_highgui.*;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameRecorder;

public class Record {
	private static int counter = 0;
	static boolean rec1 = true;

	public Record() throws Exception {
		Thread t = new Thread() {
			public void run() {
				CvCapture capture1 = cvCreateCameraCapture(CV_CAP_ANY);
				cvSetCaptureProperty(capture1, CV_CAP_PROP_FRAME_WIDTH, 320);
				cvSetCaptureProperty(capture1, CV_CAP_PROP_FRAME_HEIGHT, 240);
				IplImage hsv = cvCreateImage(cvSize(320, 240), IPL_DEPTH_8U, 3);
				String filename = "RecordVid.avi";
				String file = "RecordVid";
				String type = "avi";
				boolean filex = fileExist("RecordVid.avi");
				while (!filex) {
					String newfilename = file + counter + "." + type;
					filename = newfilename;
					System.out.println(filename);
					filex = fileExist(filename);
				}
				FrameRecorder recorder1 = new OpenCVFrameRecorder(filename,
						320, 240);
				IplImage img1;
				recorder1.setVideoCodec(1);
				recorder1.setFrameRate(15);
				recorder1.setPixelFormat(1);
				try {
					recorder1.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
				while (rec1) {
					img1 = cvQueryFrame(capture1);
					if (img1 == null)
						break;
					try {
						//cvCvtColor(img1, hsv, CV_BGR2Luv);
						recorder1.record(img1);
					} catch (Exception e) {
						e.printStackTrace();
					}
					char c = (char) cvWaitKey(15);
					if (c == 'q')
						break;
				}
				try {
					recorder1.stop();

				} catch (Exception e) {
					e.printStackTrace();
				}
				cvReleaseCapture(capture1);
				rec1 = true;
			}
		};
		t.start();
	}// record

	boolean fileExist(String name) {
		File test = new File(name);
		if (test.exists()) {
			counter++;
			return false;

		}
		return true;
	}
}
