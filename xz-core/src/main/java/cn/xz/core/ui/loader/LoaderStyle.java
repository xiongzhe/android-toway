package cn.xz.core.ui.loader;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 加载框风格
 * Created by xiongz on 2017/12/13
 */
@StringDef({LoaderStyle.BallPulseIndicator, LoaderStyle.BallGridPulseIndicator,
        LoaderStyle.BallClipRotateIndicator, LoaderStyle.BallClipRotatePulseIndicator,
        LoaderStyle.BallClipRotateMultipleIndicator, LoaderStyle.BallPulseRiseIndicator,
        LoaderStyle.BallRotateIndicator, LoaderStyle.CubeTransitionIndicator,
        LoaderStyle.BallZigZagIndicator, LoaderStyle.BallZigZagDeflectIndicator,
        LoaderStyle.BallTrianglePathIndicator, LoaderStyle.BallScaleIndicator,
        LoaderStyle.LineScaleIndicator, LoaderStyle.LineScalePartyIndicator,
        LoaderStyle.BallScaleMultipleIndicator, LoaderStyle.BallPulseSyncIndicator,
        LoaderStyle.BallBeatIndicator, LoaderStyle.LineScalePulseOutIndicator,
        LoaderStyle.LineScalePulseOutRapidIndicator, LoaderStyle.BallScaleRippleIndicator,
        LoaderStyle.BallScaleRippleMultipleIndicator, LoaderStyle.BallSpinFadeLoaderIndicator,
        LoaderStyle.LineSpinFadeLoaderIndicator, LoaderStyle.TriangleSkewSpinIndicator,
        LoaderStyle.PacmanIndicator, LoaderStyle.BallGridBeatIndicator,
        LoaderStyle.SemiCircleSpinIndicator, LoaderStyle.CustomIndicator, LoaderStyle.SquareSpinIndicator})
@Retention(RetentionPolicy.SOURCE)
public @interface LoaderStyle {
    String BallPulseIndicator = "BallPulseIndicator";
    String BallGridPulseIndicator = "BallGridPulseIndicator";
    String BallClipRotateIndicator = "BallClipRotateIndicator";
    String BallClipRotatePulseIndicator = "BallClipRotatePulseIndicator";
    String BallClipRotateMultipleIndicator = "BallClipRotateMultipleIndicator";
    String BallPulseRiseIndicator = "BallPulseRiseIndicator";
    String BallRotateIndicator = "BallRotateIndicator";
    String CubeTransitionIndicator = "CubeTransitionIndicator";
    String BallZigZagIndicator = "BallZigZagIndicator";
    String BallZigZagDeflectIndicator = "BallZigZagDeflectIndicator";
    String BallTrianglePathIndicator = "BallTrianglePathIndicator";
    String BallScaleIndicator = "BallScaleIndicator";
    String LineScaleIndicator = "LineScaleIndicator";
    String LineScalePartyIndicator = "LineScalePartyIndicator";
    String BallScaleMultipleIndicator = "BallScaleMultipleIndicator";
    String BallPulseSyncIndicator = "BallPulseSyncIndicator";
    String BallBeatIndicator = "BallBeatIndicator";
    String LineScalePulseOutIndicator = "LineScalePulseOutIndicator";
    String LineScalePulseOutRapidIndicator = "LineScalePulseOutRapidIndicator";
    String BallScaleRippleIndicator = "BallScaleRippleIndicator";
    String BallScaleRippleMultipleIndicator = "BallScaleRippleMultipleIndicator";
    String BallSpinFadeLoaderIndicator = "BallSpinFadeLoaderIndicator";
    String LineSpinFadeLoaderIndicator = "LineSpinFadeLoaderIndicator";
    String TriangleSkewSpinIndicator = "TriangleSkewSpinIndicator";
    String PacmanIndicator = "PacmanIndicator";
    String BallGridBeatIndicator = "BallGridBeatIndicator";
    String SemiCircleSpinIndicator = "SemiCircleSpinIndicator";
    String CustomIndicator = "CustomIndicator";
    String SquareSpinIndicator = "SquareSpinIndicator";
}
