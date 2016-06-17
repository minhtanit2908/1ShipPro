package com.android.test.a1shippro.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.android.test.a1shippro.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by NamNgo on 17/05/2016.
 */
public class Common {
    Context         context;
    String          tag = "loi";

    public Common(Context context) {

        this.context = context;
    }

    public Common() {
        // TODO Auto-generated constructor stub
    }

    // định dạng kiểu Date xuất ra chuỗi dạng sdf
    public String FormatDatetime(Date chuoiDateTime, int dinhDangXuat) {
        SimpleDateFormat sdf = null;
        if (dinhDangXuat == 1)
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        else if (dinhDangXuat == 2)
            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        else if (dinhDangXuat == 3)
            sdf = new SimpleDateFormat("MM/dd/yyyy");
        else if (dinhDangXuat == 4)
            sdf = new SimpleDateFormat("yyyy-MM-dd"); // Sqlite dùng kiểu ngày
        // này
        String result = "";
        try {
            result = sdf.format(chuoiDateTime);
        } catch (Exception e) {
            Log.d(tag, "Lỗi format datetime");
        }
        return result;
    }

    // Chuyển chuỗi datetime thành kiểu date-dùng cho chuỗi
    // "yyyy-MM-dd'T'HH:mm:ss"
    public Date ParseDatetime(String chuoiDateTime, int dinhDangChuoi) {

        SimpleDateFormat sdf = null;
        Date d = null;

        if (dinhDangChuoi == 1) {
            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            try {
                d = sdf.parse(chuoiDateTime);
            } catch (ParseException e) {
                Log.d(tag, "Lỗi parse datetime" + e);
            }
        } else if (dinhDangChuoi == 2) {
            sdf = new SimpleDateFormat("dd/MM/yyyy");

            try {
                d = sdf.parse(chuoiDateTime);
            } catch (ParseException e) {
                Log.d(tag, "Lỗi parse datetime" + e);
            }
        }

        return d;
    }

    // Lấy ngày cách ngày hôm nay "dayFromToday" ngày
    public String LayNgay(int cachHomNayMayNgay) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar today = Calendar.getInstance();

            today.add(Calendar.DAY_OF_MONTH, cachHomNayMayNgay);
            return sdf.format(today.getTime());
        } catch (Exception e) {
            Log.d(tag, "LayNgay: " + e);
            return "";
        }
    }

    // Định dạng số kiểu 100.000.000
    public String DinhDangChuoiTien(String chuoiSo) {
        try {
            return String.format("%,.0f", Double.parseDouble(chuoiSo));
        } catch (Exception e) {
            Log.d(tag, "DinhDangChuoiTien: " + e);
            return "";
        }
    }

    // Định dạng số kiểu 100.000.000,234234
    public String DinhDangChuoiSoLuong(String chuoiSo) {
        try {
            return String.format("%,.1f", Double.parseDouble(chuoiSo));
        } catch (Exception e) {
            Log.d(tag, "DinhDangChuoiSoLuong: " + e);
            return "";
        }
    }

    // Mã hóa mật khẩu sang md5
    public String convertToMd5(final String md5) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        try {

            final java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            final byte[] array = md.digest(md5.getBytes("UTF-8"));
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (final java.security.NoSuchAlgorithmException e) {
        }
        return sb.toString();
    }

    // Ẩn bàn phím khi click outside edittext
    public void hideSoftKeyboard(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {

        try {
            // Set up touch listener for non-text box views to hide keyboard.
            if (!(view instanceof EditText)) {

                view.setOnTouchListener(new View.OnTouchListener() {

                    public boolean onTouch(View v, MotionEvent event) {
                        hideSoftKeyboard(context);
                        return false;
                    }

                });
            }

            // If a layout container, iterate over children and seed recursion.
            if (view instanceof ViewGroup) {

                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                    View innerView = ((ViewGroup) view).getChildAt(i);

                    setupUI(innerView);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // END //Ẩn bàn phím



    // Convert dp to pixel
    public int Convert_DP_TO_PIXEL(int dp) {

        try {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                    context.getResources().getDisplayMetrics());
        } catch (Exception e) {
            Log.d(tag, "Convert_DP_TO_PIXEL: " + e);
            return 0;
        }

    }
    // END convert

    public void XoaFileTrongThuMuc(String duongDanThuMuc, String duoiFile) {
        try {
            File[] folderList = LayListFileTrongThuMuc(duongDanThuMuc, duoiFile);

            for (int i = 0; i < folderList.length; i++) {
                File file = new File(folderList[i].getPath());
                file.delete();
            }
        } catch (Exception e) {
            Log.d(tag, "XoaFileTrongThuMuc: " + e);
        }
    }

    public File[] LayListFileTrongThuMuc(String duongDanThuMuc, final String duoiFile) {
        //Sử dụng
        //Lấy list file
        //File[] folderList = thanhNam.LayListFileTrongThuMuc(duongDanThuMucGoc, ".3gpp");

        //Lấy list thư mục con
        //File[] folderList = thanhNam.LayListFileTrongThuMuc(duongDanThuMucGoc, null);

        File[] folderList = null;
        try {
            File folder = new File(duongDanThuMuc);

            FilenameFilter filter = new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return (name.endsWith(duoiFile));
                }
            };

            if (duoiFile != null)
                folderList = folder.listFiles(filter);
            else
                folderList = folder.listFiles();

        } catch (Exception e) {
            Log.d(tag, "LayListFileTrongThuMuc: " + e);
        }
        return folderList;
    }

    public void log(String s)
    {
        try {
            Log.d(tag, s);
        } catch (Exception e) {

        }
    }
    public static String timeStampToDate(long timeStamp) {
        try{
            Date netDate = (new Date(timeStamp));
            return new SimpleDateFormat("MM/dd/yyyy").format(netDate);
        }
        catch(Exception ex){
            return "";
        }
    }

    public static String slipteStringDate(String date){
        String[] timeDate = date.split(" ");
        return timeDate[0];
    }

    public final  long ONE_SECOND = 1000;
    public final  long ONE_MINUTE = ONE_SECOND * 60;
    public final  long ONE_HOUR = ONE_MINUTE * 60;
    public final  long ONE_DAY = ONE_HOUR * 24;
    public final  long MONTHS = ONE_DAY * 30;
    public final  long YEARS = MONTHS * 12;

    public String millisToLongDHMS(String uploadTime) {
        Calendar c = Calendar.getInstance();
        Long duration = c.getTimeInMillis() - Long.parseLong(uploadTime);
        StringBuffer res = new StringBuffer();
        long temp = 0;
        if (duration >= ONE_SECOND) {
            temp = duration / YEARS;
            if (temp > 0) {
                if(temp > 1)
                    res.append(temp).append(" " + context.getResources().getString(R.string.years));
                else
                    res.append(temp).append(" " + " " + context.getResources().getString(R.string.year));

                return res.toString();
            }

            temp = duration / MONTHS;
            if (temp > 0) {
                if(temp > 1)
                    res.append(temp).append(" " + context.getResources().getString(R.string.months));
                else
                    res.append(temp).append(" " + context.getResources().getString(R.string.month));

                return res.toString();
            }


            temp = duration / ONE_DAY;
            if (temp > 0) {
                if(temp > 1)
                    res.append(temp).append(" " + context.getResources().getString(R.string.days));
                else
                    res.append(temp).append(" " + context.getResources().getString(R.string.day));

                return res.toString();
            }

            temp = duration / ONE_HOUR;
            if (temp > 0) {
                if(temp > 1)
                    res.append(temp).append(" " + context.getResources().getString(R.string.hours));
                else
                    res.append(temp).append(" " + context.getResources().getString(R.string.hour));

                return res.toString();

            }

            temp = duration / ONE_MINUTE;
            if (temp > 0) {
                if(temp > 1)
                    res.append(temp).append(" " + context.getResources().getString(R.string.minutes));
                else
                    res.append(temp).append(" " + context.getResources().getString(R.string.minute));

                return res.toString();
            }

            temp = duration / ONE_SECOND;
            if (temp > 0) {
                if(temp > 1)
                    res.append(temp).append(" " + context.getResources().getString(R.string.seconds));
                else
                    res.append(temp).append(" " + context.getResources().getString(R.string.second));

                return res.toString();
            }
            return res.toString();
        } else {
            return "0 second";
        }
    }

    public static byte[] convertFileToByte(String path) {
        Bitmap bm = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        return b;
    }
    public static byte[] convertBitmapToByte(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        return b;
    }
    public static Bitmap resize(Bitmap b, int alpha) {
        Matrix m = new Matrix();
        m.setRectToRect(new RectF(0, 0, b.getWidth(), b.getHeight()), new RectF(0, 0, 1000, 1000), Matrix.ScaleToFit.CENTER);
        m.postRotate(alpha);
        return Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
    }
    //
}//END
