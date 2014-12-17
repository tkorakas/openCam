package opencv;

import org.bytedeco.*;
import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_highgui;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.FrameRecorder.Exception;
import org.bytedeco.javacv.OpenCVFrameRecorder;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_calib3d.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;
import static org.bytedeco.javacpp.opencv_highgui.*;


public class OpenCamera {

	private CvCapture capture;

	public OpenCamera() {
		Thread t = new Thread() {
			public void run() {
				capture = cvCreateCameraCapture(CV_CAP_ANY);
				cvSetCaptureProperty(capture, CV_CAP_PROP_FRAME_WIDTH, 320);
				cvSetCaptureProperty(capture, CV_CAP_PROP_FRAME_HEIGHT, 240);
				final CanvasFrame camera = new CanvasFrame("Camera");

				// cvNamedWindow("LiveVid", CV_WINDOW_AUTOSIZE);
				// IplImage hsv = cvCreateImage(cvSize(320, 240),
				// IPL_DEPTH_8U,
				// 3);
				IplImage img1;
				camera.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
				while (camera.isVisible()
						&& (img1 = opencv_highgui.cvQueryFrame(capture)) != null) {
					img1 = cvQueryFrame(capture);
					if (img1 == null)
						break;
					// cvCvtColor(img1, hsv, CV_BGR2Luv);
					camera.showImage(img1);

					// cvShowImage("LiveVid", img1);
					
					 char c = (char)cvWaitKey(15); if (c == 'q') break;
					 
				}
				//cvRelease(capture);
				cvReleaseCapture(capture);
				if(!MainFrame.frame.isActive())MainFrame.frame.show();
				
			}
		};
		t.start();
	}
}
