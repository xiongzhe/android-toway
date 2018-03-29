package cn.xz.core.delegates;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.blankj.utilcode.util.ToastUtils;
import com.yalantis.ucrop.UCrop;

import cn.xz.core.permissions.PermissionCallBack;
import cn.xz.core.ui.camera.CameraImageBean;
import cn.xz.core.ui.camera.XzCamera;
import cn.xz.core.ui.camera.RequestCodes;
import cn.xz.core.util.callback.CallbackManager;
import cn.xz.core.util.callback.CallbackType;
import cn.xz.core.util.callback.IGlobalCallback;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Android 6.0权限 Fragment基类
 * Created by xiongz on 2017/12/10.
 */
@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {

    /**
     * 调用获取相机权限
     */
    public void startCamera(PermissionCallBack callBack) {
        PermissionCheckerDelegatePermissionsDispatcher.cameraWithPermissionCheck(this, callBack);
    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void camera(PermissionCallBack callBack) {
        callBack.handle();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void cameraRationale(final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setMessage("使用该功能需要获取相机权限，下一步将继续请求权限")
                .setPositiveButton("下一步", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();
            }
        }).show();
    }

    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void cameraDenied() {
        ToastUtils.showLong("已拒绝获取相机权限");
    }

    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void cameraNeverAsk() {
        ToastUtils.showLong("已拒绝获取相机权限，并不再询问");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCodes.TAKE_PHOTO:
                    final Uri resultUri = CameraImageBean.getInstance().getPath();
                    UCrop.of(resultUri, resultUri)
                            .withMaxResultSize(400, 400)
                            .start(getContext(), this);
                    break;
                case RequestCodes.PICK_PHOTO:
                    if (data != null) {
                        final Uri pickPath = data.getData();
                        //从相册选择后需要有个路径存放剪裁过的图片地址
                        final String pickCropPath = XzCamera.createCropFile().getPath();
                        UCrop.of(pickPath, Uri.parse(pickCropPath))
                                .withMaxResultSize(400, 400)
                                .start(getContext(), this);
                    }
                    break;
                case RequestCodes.CROP_PHOTO:
                    final Uri cropUri = UCrop.getOutput(data);
                    //拿到剪裁后的数据进行处理
                    @SuppressWarnings("unchecked")
                    final IGlobalCallback<Uri> callback = CallbackManager
                            .getInstance()
                            .getCallback(CallbackType.ON_CROP);
                    if (callback != null) {
                        callback.executeCallback(cropUri);
                    }
                    break;
                case RequestCodes.CROP_ERROR:
                    ToastUtils.showShort("剪裁出错");
                    break;
                default:
                    break;
            }
        } else {
            final Throwable cropError = UCrop.getError(data);
            ToastUtils.showShort(cropError.getMessage());
        }
    }

    /**
     * 调用获取读取文件权限
     */
    public void startStorage(PermissionCallBack callBack) {
        PermissionCheckerDelegatePermissionsDispatcher.storageWithPermissionCheck(this, callBack);
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void storage(PermissionCallBack callBack) {
        callBack.handle();
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void storageRationale(final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setMessage("使用该功能需要获取读写文件权限，下一步将继续请求权限")
                .setPositiveButton("下一步", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();
            }
        }).show();
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void storageDenied() {
        ToastUtils.showLong("已拒绝获取读写文件权限");
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void storageNeverAsk() {
        ToastUtils.showLong("已拒绝获取读写文件权限，并不再询问");
    }

    /**
     * 调用获取打电话权限
     */
    public void startCall(PermissionCallBack callBack) {
        PermissionCheckerDelegatePermissionsDispatcher.callWithPermissionCheck(this, callBack);
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void call(PermissionCallBack callBack) {
        callBack.handle();
    }

    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void callRationale(final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setMessage("使用该功能需要获取打电话权限，下一步将继续请求权限")
                .setPositiveButton("下一步", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();
            }
        }).show();
    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    void callDenied() {
        ToastUtils.showLong("已拒绝获取打电话权限");
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void callNeverAsk() {
        ToastUtils.showLong("已拒绝获取打电话权限，并不再询问");
    }
}
