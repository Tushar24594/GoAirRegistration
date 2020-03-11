package in.tushar.goairregistration;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CSV {
    private static final String File_Header = "Name, Phone Number, E-mail, Date";
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    File database;
    public static final String File_Name = "RegistrationData.csv";
    public static final String TAG = "--CSV Activity--";
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyy hh:mm:ss aa");
    String date, image;
    Boolean isSaved = false;

    protected Boolean saveDatatoCSV(String userName, String userMobile, final String userEmail) {
        try {
            date = simpleDateFormat.format(calendar.getTime());
            database = new File(Environment.getExternalStorageDirectory() + "/" + File_Name);
            if (!database.exists()) {
                try {
                    database.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fileWriter = new FileWriter(database.getAbsoluteFile(), true);
            bufferedWriter = new BufferedWriter(fileWriter);
            int i = (int) database.length();
            if (i != 0) {
                bufferedWriter.write(userName + "," + userMobile + "," + userEmail + "," + date + "\n");
            } else if (i == 0) {
                bufferedWriter.write(File_Header);
                bufferedWriter.write("\n");
                bufferedWriter.write(userName + "," + userMobile + "," + userEmail + "," + date + "\n");
            }
            isSaved = true;
        } catch (Exception fnof) {
            fnof.printStackTrace();
            isSaved = false;
            return isSaved;
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                    fileWriter.close();
                } else {
                    Log.e(TAG, "Close");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isSaved;
    }
}
