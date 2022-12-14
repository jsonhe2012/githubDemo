package com.mt.mygithub.utils;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mt.mygithub.R;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: jasonhe .
 * @data: On 2022/8/4
 */
public class FragmentSwitchTool implements OnClickListener {

    public interface OnClickListener {
        void onClick(View view);
    }

    public interface OnDoubleClickListener {
        void onDoubleClick(View view);
    }


    private FragmentManager mFragmentManager;
    private int mContainerId;
    private boolean mShowAnimator;
    private List<View> mClickableViews;
    private Fragment[] mFragments;
    private OnClickListener mOnClickListener;
    private OnDoubleClickListener mOnDoubleOnClickListener;

    private Fragment mCurrentFragment;
    private View mCurrentSelectedView;

    public void setClickableViews(View... clickableViews) {
        this.mClickableViews = Arrays.asList(clickableViews);
        for (View view : clickableViews) {
            view.setOnClickListener(this);
        }
    }

    public void setClickableViewsAndListener(OnClickListener onClickListener, View... clickableViews) {
        mOnClickListener = onClickListener;
    }

    public void setFragments(Fragment... fragments) {
        this.mFragments = fragments;
    }

    public void changeTag(View targetView) {

        if (targetView == mCurrentSelectedView) {
            return;
        }

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        Fragment targetFragment = mFragmentManager.findFragmentByTag(String.valueOf(targetView.getId()));
        int targetPosition = mClickableViews.indexOf(targetView);
        int currentPosition = mClickableViews.indexOf(mCurrentSelectedView);

        if (mShowAnimator) {
            if (targetPosition > currentPosition) {
                fragmentTransaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out);
            } else if (targetPosition < currentPosition) {
                fragmentTransaction.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_right_out);
            }
        }


        if (targetFragment == null) {
            if (mCurrentFragment != null) {
                fragmentTransaction.hide(mCurrentFragment);
                onSelectedView(false, mCurrentSelectedView);
            }
            targetFragment = mFragments[targetPosition];

            fragmentTransaction.add(mContainerId, targetFragment, String.valueOf(targetView.getId()));
        } else {
            fragmentTransaction.hide(mCurrentFragment);

            onSelectedView(false, mCurrentSelectedView);
            fragmentTransaction.show(targetFragment);
        }

        fragmentTransaction.commit();
        mCurrentFragment = targetFragment;
        mCurrentSelectedView = targetView;
        onSelectedView(true, mCurrentSelectedView);
    }

    void onSelectedView(boolean selected, View view) {
        if (mCurrentSelectedView instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) mCurrentSelectedView).getChildCount(); i++) {
                View child = ((ViewGroup) mCurrentSelectedView).getChildAt(i);
                if (child == null)
                    continue;
                if (child.getVisibility() == View.VISIBLE) {
                    child.setSelected(selected);
                }
            }
        }
        view.setSelected(selected);
    }

    @Override
    public void onClick(View v) {
        changeTag(v);
    }

    public static final class Builder {
        FragmentManager mFragmentManager;
        int mContainerId;
        boolean mShowAnimator;
        List<View> mClickableViews; //????????????????????????view,???????????????LinearLayout
        Fragment[] mFragments;
        OnClickListener mOnClickListener;
        OnDoubleClickListener mOnDoubleClickListener;

        /**
         * ???????????? Fragment?????????
         *
         * @param fragmentManager fragmentManager
         * @return Builder
         */
        public Builder fragmentManager(FragmentManager fragmentManager) {
            this.mFragmentManager = fragmentManager;
            return this;
        }

        /**
         * ???????????? fragment???????????????id
         *
         * @param containerId containerId
         * @return Builder
         */
        public Builder containerId(int containerId) {
            this.mContainerId = containerId;
            return this;
        }

        /**
         * ???????????? Tab?????????????????????
         *
         * @param clickableViews clickableViews
         * @return Builder
         */
        public Builder clickableViews(View... clickableViews) {
            this.mClickableViews = Arrays.asList(clickableViews);
            return this;
        }

        /**
         * ???????????? Tab?????????Fragment
         *
         * @param fragments fragments
         * @return Builder
         */
        public Builder fragments(Fragment... fragments) {
            this.mFragments = fragments;
            return this;
        }

        /**
         * ???????????? ??????????????????
         *
         * @param showAnimator showAnimator
         * @return Builder
         */
        public Builder showAnimator(boolean showAnimator) {
            this.mShowAnimator = showAnimator;
            return this;
        }

        /**
         * ???????????? Tab???????????????
         *
         * @param onClickListener onClickListener
         * @return Builder
         */
        public Builder onClickListener(OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
            return this;
        }

        /**
         * ???????????? Tab???????????????
         *
         * @param onDoubleClickListener onDoubleClickListener
         * @return Builder
         */
        public Builder onDoubleClickListener(OnDoubleClickListener onDoubleClickListener) {
            this.mOnDoubleClickListener = onDoubleClickListener;
            return this;
        }

        public FragmentSwitchTool build() {
            final FragmentSwitchTool fragmentSwitchTool = new FragmentSwitchTool();
            fragmentSwitchTool.mFragmentManager = mFragmentManager;
            fragmentSwitchTool.mContainerId = mContainerId;
            fragmentSwitchTool.mShowAnimator = mShowAnimator;
            fragmentSwitchTool.mClickableViews = mClickableViews;
            fragmentSwitchTool.mFragments = mFragments;
            fragmentSwitchTool.mOnClickListener = mOnClickListener;
            fragmentSwitchTool.mOnDoubleOnClickListener = mOnDoubleClickListener;

            if (mOnDoubleClickListener == null) {
                for (View view : mClickableViews) {
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fragmentSwitchTool.changeTag(view);
                            if (mOnClickListener != null) {
                                mOnClickListener.onClick(view);
                            }
                        }
                    });
                }
            } else {
                for (final View view : mClickableViews) {
                    final GestureDetector gestureDetector = new GestureDetector(mClickableViews.get(0).getContext(), new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                            fragmentSwitchTool.changeTag(view);
                            if (fragmentSwitchTool.mOnClickListener != null) {
                                fragmentSwitchTool.mOnClickListener.onClick(view);
                            }
                            return true;
                        }

                        @Override
                        public boolean onDown(MotionEvent e) {
                            return true;
                        }

                        @Override
                        public boolean onDoubleTap(MotionEvent e) {
                            fragmentSwitchTool.mOnDoubleOnClickListener.onDoubleClick(view);
                            return true;
                        }
                    });

                    view.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return gestureDetector.onTouchEvent(event);
                        }
                    });
                }
            }
            return fragmentSwitchTool;
        }
    }
}