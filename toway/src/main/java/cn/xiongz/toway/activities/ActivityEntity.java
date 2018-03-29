package cn.xiongz.toway.activities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 活动实体
 * Created by xiongz on 2018/3/29.
 */
public class ActivityEntity implements Parcelable {

    //活动发布时间
    private String activity_date_text;
    //活动地址
    private String address;
    //活动截止时间
    private String end_date;
    //活动id
    private int id;
    //活动已报名人数
    private int num;
    //活动封面图片
    private String pic;
    //活动价格
    private String price;
    //活动开始时间
    private String start_date;
    //显示的活动开始时间
    private String start_date_text;
    //1.正常 0.拉黑
    private int state;
    //1.报名中 2.活动中 3.活动结束
    private int status;
    //活动名称
    private String title;
    //置顶
    private int top_time;
    //活动人数限制（等于0时即为无人数限制）
    private int total;
    private UserBean user;

    public String getActivity_date_text() {
        return activity_date_text;
    }

    public void setActivity_date_text(String activity_date_text) {
        this.activity_date_text = activity_date_text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getStart_date_text() {
        return start_date_text;
    }

    public void setStart_date_text(String start_date_text) {
        this.start_date_text = start_date_text;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTop_time() {
        return top_time;
    }

    public void setTop_time(int top_time) {
        this.top_time = top_time;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean implements Parcelable {
        //发起活动人头像
        private String avatar_url;
        //发起活动人id
        private int id;
        //发起活动人名称
        private String name;

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.avatar_url);
            dest.writeInt(this.id);
            dest.writeString(this.name);
        }

        public UserBean() {
        }

        protected UserBean(Parcel in) {
            this.avatar_url = in.readString();
            this.id = in.readInt();
            this.name = in.readString();
        }

        public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
            @Override
            public UserBean createFromParcel(Parcel source) {
                return new UserBean(source);
            }

            @Override
            public UserBean[] newArray(int size) {
                return new UserBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.activity_date_text);
        dest.writeString(this.address);
        dest.writeString(this.end_date);
        dest.writeInt(this.id);
        dest.writeInt(this.num);
        dest.writeString(this.pic);
        dest.writeString(this.price);
        dest.writeString(this.start_date);
        dest.writeString(this.start_date_text);
        dest.writeInt(this.state);
        dest.writeInt(this.status);
        dest.writeString(this.title);
        dest.writeInt(this.top_time);
        dest.writeInt(this.total);
        dest.writeParcelable(this.user, flags);
    }

    public ActivityEntity() {
    }

    protected ActivityEntity(Parcel in) {
        this.activity_date_text = in.readString();
        this.address = in.readString();
        this.end_date = in.readString();
        this.id = in.readInt();
        this.num = in.readInt();
        this.pic = in.readString();
        this.price = in.readString();
        this.start_date = in.readString();
        this.start_date_text = in.readString();
        this.state = in.readInt();
        this.status = in.readInt();
        this.title = in.readString();
        this.top_time = in.readInt();
        this.total = in.readInt();
        this.user = in.readParcelable(UserBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ActivityEntity> CREATOR = new Parcelable.Creator<ActivityEntity>() {
        @Override
        public ActivityEntity createFromParcel(Parcel source) {
            return new ActivityEntity(source);
        }

        @Override
        public ActivityEntity[] newArray(int size) {
            return new ActivityEntity[size];
        }
    };
}
