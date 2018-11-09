package app.music.il.co.musicapp.pl;

import android.app.ProgressDialog;
import android.content.Context;

import app.music.il.co.musicapp.R;

public class LoadingDialog extends ProgressDialog {
    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }
    public void createLoadingDialog(String title, String body){
        this.setTitle(super.getContext().getString(R.string.loading_title));
        this.setMessage(super.getContext().getString(R.string.loading_message));
    }
}
