package com.danielburgnerjr.appume2019;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class ContactMeActivity extends Activity {

    public ContactMeActivity() {
    }

    public void launchContactMethod(Context context, int position) {
        Intent newActivity;
        String strPackName = context.getApplicationContext().getPackageName();

        switch(position) {
            case 0:
                String strEmail = "dburgnerjr@yahoo.com";
                newActivity = new Intent(Intent.ACTION_SEND);
                newActivity.putExtra(Intent.EXTRA_EMAIL, new String[]{strEmail});
                newActivity.setType("plain/text");
                context.startActivity(newActivity);
                break;

            case 1:
                String strPhone = "tel:1-757-202-5849";
                try {
                    newActivity = new Intent(Intent.ACTION_DIAL);
                    newActivity.setData(Uri.parse(strPhone));
                    context.startActivity(newActivity);
                } catch (Exception e) {
                    Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;

            case 2:
                String strLinkedIn = "http://www.linkedin.com/in/dburgnerjr";
                newActivity = new Intent(Intent.ACTION_VIEW,  Uri.parse(strLinkedIn));
                context.startActivity(newActivity);
                break;

            case 3:
                String strGitHub = "https://github.com/dburgnerjr";
                newActivity = new Intent(Intent.ACTION_VIEW,  Uri.parse(strGitHub));
                context.startActivity(newActivity);
                break;

            case 4:
                String strStackOverflow = "http://stackoverflow.com/users/5111305/daniel-burgner";
                newActivity = new Intent(Intent.ACTION_VIEW,  Uri.parse(strStackOverflow));
                context.startActivity(newActivity);
                break;

            case 5:
                String strTwitter = "https://twitter.com/dburgnerjr";
                newActivity = new Intent(Intent.ACTION_VIEW,  Uri.parse(strTwitter));
                context.startActivity(newActivity);
                break;

            case 6:
                String strPlayStore = "market://search?q=pub:Daniel Burgner, Jr.";
                newActivity = new Intent(Intent.ACTION_VIEW,  Uri.parse(strPlayStore));
                try {
                    context.startActivity(newActivity);
                } catch (ActivityNotFoundException e) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=Daniel Burgner, Jr.")));
                }
                break;

            case 7:
                Uri uri = Uri.parse("market://details?id=" + strPackName);
                newActivity = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    context.startActivity(newActivity);
                } catch (ActivityNotFoundException e) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + strPackName)));
                }
                break;

            case 8:
                try {
                    newActivity = new Intent(Intent.ACTION_SEND);
                    newActivity.setType("text/plain");
                    newActivity.putExtra(Intent.EXTRA_SUBJECT, "Appume");
                    String sAux = "\nLet me recommend you this application\n\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=" + strPackName + "\n\n";
                    newActivity.putExtra(Intent.EXTRA_TEXT, sAux);
                    context.startActivity(Intent.createChooser(newActivity, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
                break;
        }
    }
}
