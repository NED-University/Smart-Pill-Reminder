package com.taradov.alarmme;

/**
 * Created by Administrator on 4/13/2015.
 */


        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.widget.RelativeLayout;
        import android.widget.RelativeLayout.LayoutParams;

public class ColorDialog extends AlertDialog {

    private ColorPicker2 colorPickerView;
    private final OnColorSelectedListener onColorSelectedListener;

    public ColorDialog(Context context, int initialColor, OnColorSelectedListener onColorSelectedListener) {
        super(context);

        this.onColorSelectedListener = onColorSelectedListener;

        RelativeLayout relativeLayout = new RelativeLayout(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        colorPickerView = new ColorPicker2(context);
        colorPickerView.setColor(initialColor);

        relativeLayout.addView(colorPickerView, layoutParams);

        setButton(BUTTON_POSITIVE, context.getString(android.R.string.ok), onClickListener);
        setButton(BUTTON_NEGATIVE, context.getString(android.R.string.cancel), onClickListener);

        setView(relativeLayout);

    }

    private OnClickListener onClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case BUTTON_POSITIVE:
                    int selectedColor = colorPickerView.getColor();
                    onColorSelectedListener.onColorSelected(selectedColor);
                    break;
                case BUTTON_NEGATIVE:
                    dialog.dismiss();
                    break;
            }
        }
    };

    public interface OnColorSelectedListener {
        public void onColorSelected(int color);
    }

}
