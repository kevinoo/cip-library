package it.lucichkevin.cip.navigationdrawermenu;

/**
 * Created by kevin on 30/08/2014.
 */
public class ItemDrawerMenu {

    private Integer title;      //  Resource
    private Integer image;      //  Resource
    private Class classOfActivity;
    private OnClickListener onClickListener = null;

    public ItemDrawerMenu( Integer title, Class classOfActivity ){
        this( title, null, classOfActivity, null );
    }

    public ItemDrawerMenu( Integer title, OnClickListener onClickListener ){
        this( title, null, null, onClickListener );
    }

    public ItemDrawerMenu( Integer title, Integer image, Class classOfActivity ){
        this( title, image, classOfActivity, null );
    }

    public ItemDrawerMenu( Integer title, Integer image, Class classOfActivity, OnClickListener onClickListener ){
        this.title = title;
        this.image = image;
        this.classOfActivity = classOfActivity;
        this.onClickListener = onClickListener;
    }

    public Integer getTitle() {
        return title;
    }
    public void setTitle(Integer title) {
        this.title = title;
    }

    public Integer getImage() {
        return image;
    }
    public void setImage(Integer image) {
        this.image = image;
    }

    public Class getClassOfActivity() {
        return classOfActivity;
    }
    public void setClassOfActivity(Class classOfActivity) {
        this.classOfActivity = classOfActivity;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    //  Callback
    public static interface OnClickListener{
        public void onClick();
    }

}