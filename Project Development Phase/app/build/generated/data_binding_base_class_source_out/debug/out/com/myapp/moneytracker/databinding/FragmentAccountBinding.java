// Generated by view binder compiler. Do not edit!
package com.myapp.moneytracker.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.github.mikephil.charting.charts.BarChart;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.myapp.moneytracker.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentAccountBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final LinearLayout accountInfo;

  @NonNull
  public final MaterialCardView allTimeCard;

  @NonNull
  public final TextView allTimeTitle;

  @NonNull
  public final BarChart barChart;

  @NonNull
  public final ImageButton btnLogout;

  @NonNull
  public final MaterialButton buttonDate;

  @NonNull
  public final MaterialCardView chartCard;

  @NonNull
  public final MaterialCardView chartMenu;

  @NonNull
  public final TextView chartTitle;

  @NonNull
  public final TextView expenseAmount;

  @NonNull
  public final TextView incomeAmount;

  @NonNull
  public final ImageView line;

  @NonNull
  public final LinearLayout linearAmountTitle;

  @NonNull
  public final TextView netAmount;

  @NonNull
  public final TextView netAmountRange;

  @NonNull
  public final TextView netAmountRangeTitle;

  @NonNull
  public final TextView netAmountTitle;

  @NonNull
  public final TextView picture;

  @NonNull
  public final SwipeRefreshLayout swipeRefresh;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final TextView tvEmail;

  @NonNull
  public final TextView tvName;

  @NonNull
  public final RelativeLayout upperLayout;

  @NonNull
  public final MaterialCardView verified;

  private FragmentAccountBinding(@NonNull RelativeLayout rootView,
      @NonNull LinearLayout accountInfo, @NonNull MaterialCardView allTimeCard,
      @NonNull TextView allTimeTitle, @NonNull BarChart barChart, @NonNull ImageButton btnLogout,
      @NonNull MaterialButton buttonDate, @NonNull MaterialCardView chartCard,
      @NonNull MaterialCardView chartMenu, @NonNull TextView chartTitle,
      @NonNull TextView expenseAmount, @NonNull TextView incomeAmount, @NonNull ImageView line,
      @NonNull LinearLayout linearAmountTitle, @NonNull TextView netAmount,
      @NonNull TextView netAmountRange, @NonNull TextView netAmountRangeTitle,
      @NonNull TextView netAmountTitle, @NonNull TextView picture,
      @NonNull SwipeRefreshLayout swipeRefresh, @NonNull Toolbar toolbar, @NonNull TextView tvEmail,
      @NonNull TextView tvName, @NonNull RelativeLayout upperLayout,
      @NonNull MaterialCardView verified) {
    this.rootView = rootView;
    this.accountInfo = accountInfo;
    this.allTimeCard = allTimeCard;
    this.allTimeTitle = allTimeTitle;
    this.barChart = barChart;
    this.btnLogout = btnLogout;
    this.buttonDate = buttonDate;
    this.chartCard = chartCard;
    this.chartMenu = chartMenu;
    this.chartTitle = chartTitle;
    this.expenseAmount = expenseAmount;
    this.incomeAmount = incomeAmount;
    this.line = line;
    this.linearAmountTitle = linearAmountTitle;
    this.netAmount = netAmount;
    this.netAmountRange = netAmountRange;
    this.netAmountRangeTitle = netAmountRangeTitle;
    this.netAmountTitle = netAmountTitle;
    this.picture = picture;
    this.swipeRefresh = swipeRefresh;
    this.toolbar = toolbar;
    this.tvEmail = tvEmail;
    this.tvName = tvName;
    this.upperLayout = upperLayout;
    this.verified = verified;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAccountBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_account, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAccountBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.accountInfo;
      LinearLayout accountInfo = ViewBindings.findChildViewById(rootView, id);
      if (accountInfo == null) {
        break missingId;
      }

      id = R.id.allTimeCard;
      MaterialCardView allTimeCard = ViewBindings.findChildViewById(rootView, id);
      if (allTimeCard == null) {
        break missingId;
      }

      id = R.id.allTimeTitle;
      TextView allTimeTitle = ViewBindings.findChildViewById(rootView, id);
      if (allTimeTitle == null) {
        break missingId;
      }

      id = R.id.barChart;
      BarChart barChart = ViewBindings.findChildViewById(rootView, id);
      if (barChart == null) {
        break missingId;
      }

      id = R.id.btnLogout;
      ImageButton btnLogout = ViewBindings.findChildViewById(rootView, id);
      if (btnLogout == null) {
        break missingId;
      }

      id = R.id.buttonDate;
      MaterialButton buttonDate = ViewBindings.findChildViewById(rootView, id);
      if (buttonDate == null) {
        break missingId;
      }

      id = R.id.chartCard;
      MaterialCardView chartCard = ViewBindings.findChildViewById(rootView, id);
      if (chartCard == null) {
        break missingId;
      }

      id = R.id.chartMenu;
      MaterialCardView chartMenu = ViewBindings.findChildViewById(rootView, id);
      if (chartMenu == null) {
        break missingId;
      }

      id = R.id.chartTitle;
      TextView chartTitle = ViewBindings.findChildViewById(rootView, id);
      if (chartTitle == null) {
        break missingId;
      }

      id = R.id.expenseAmount;
      TextView expenseAmount = ViewBindings.findChildViewById(rootView, id);
      if (expenseAmount == null) {
        break missingId;
      }

      id = R.id.incomeAmount;
      TextView incomeAmount = ViewBindings.findChildViewById(rootView, id);
      if (incomeAmount == null) {
        break missingId;
      }

      id = R.id.line;
      ImageView line = ViewBindings.findChildViewById(rootView, id);
      if (line == null) {
        break missingId;
      }

      id = R.id.linearAmountTitle;
      LinearLayout linearAmountTitle = ViewBindings.findChildViewById(rootView, id);
      if (linearAmountTitle == null) {
        break missingId;
      }

      id = R.id.netAmount;
      TextView netAmount = ViewBindings.findChildViewById(rootView, id);
      if (netAmount == null) {
        break missingId;
      }

      id = R.id.netAmountRange;
      TextView netAmountRange = ViewBindings.findChildViewById(rootView, id);
      if (netAmountRange == null) {
        break missingId;
      }

      id = R.id.netAmountRangeTitle;
      TextView netAmountRangeTitle = ViewBindings.findChildViewById(rootView, id);
      if (netAmountRangeTitle == null) {
        break missingId;
      }

      id = R.id.netAmountTitle;
      TextView netAmountTitle = ViewBindings.findChildViewById(rootView, id);
      if (netAmountTitle == null) {
        break missingId;
      }

      id = R.id.picture;
      TextView picture = ViewBindings.findChildViewById(rootView, id);
      if (picture == null) {
        break missingId;
      }

      id = R.id.swipeRefresh;
      SwipeRefreshLayout swipeRefresh = ViewBindings.findChildViewById(rootView, id);
      if (swipeRefresh == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.tvEmail;
      TextView tvEmail = ViewBindings.findChildViewById(rootView, id);
      if (tvEmail == null) {
        break missingId;
      }

      id = R.id.tvName;
      TextView tvName = ViewBindings.findChildViewById(rootView, id);
      if (tvName == null) {
        break missingId;
      }

      id = R.id.upperLayout;
      RelativeLayout upperLayout = ViewBindings.findChildViewById(rootView, id);
      if (upperLayout == null) {
        break missingId;
      }

      id = R.id.verified;
      MaterialCardView verified = ViewBindings.findChildViewById(rootView, id);
      if (verified == null) {
        break missingId;
      }

      return new FragmentAccountBinding((RelativeLayout) rootView, accountInfo, allTimeCard,
          allTimeTitle, barChart, btnLogout, buttonDate, chartCard, chartMenu, chartTitle,
          expenseAmount, incomeAmount, line, linearAmountTitle, netAmount, netAmountRange,
          netAmountRangeTitle, netAmountTitle, picture, swipeRefresh, toolbar, tvEmail, tvName,
          upperLayout, verified);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
