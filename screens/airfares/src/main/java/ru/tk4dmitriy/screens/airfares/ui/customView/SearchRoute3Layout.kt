package ru.tk4dmitriy.screens.airfares.ui.customView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.os.Parcel
import android.os.Parcelable
import android.text.InputType
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import ru.tk4dmitriy.screens.airfares.R

@SuppressLint("ClickableViewAccessibility")
internal class SearchRoute3Layout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): ViewGroup(context, attrs) {
    private val basePaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            val cornerRadius = resources.getDimension(R.dimen.sr3_base_corner_radius)
            pathEffect = CornerPathEffect(cornerRadius)
        }
    }
    private val linePaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    private val icSwap: ImageView by lazy {
        ImageView(context).apply {
            setImageResource(R.drawable.ic_swap)
        }
    }

    private val icClear: ImageView by lazy {
        ImageView(context).apply {
            setImageResource(R.drawable.ic_cancel)
        }
    }

    private val icBack: ImageView by lazy {
        ImageView(context).apply {
            setImageResource(R.drawable.ic_back)
        }
    }

    private val departureTouchListener = OnTouchListener { v, event ->
        if (event?.action == MotionEvent.ACTION_UP &&
            event.rawX >= arrivalEditText.right - arrivalEditText.getCompoundDrawables()[2]
                .getBounds().width()
        ) {
            departureEditText.text = arrivalEditText.text.also {
                arrivalEditText.text = departureEditText.text
            }
            return@OnTouchListener true
        }
        false
    }

    private val arrivalTouchListener = OnTouchListener { v, event ->
        if (event?.action == MotionEvent.ACTION_UP &&
            event.rawX >= arrivalEditText.right - arrivalEditText.getCompoundDrawables()[2]
                .getBounds().width()
        ) {
            arrivalEditText.setText("")
            return@OnTouchListener true
        }
        false
    }

    val departureEditText: EditText by lazy {
        val hint = resources.getString(R.string.departure_hint)
        EditText(context).applySettings(hint).apply {
            isFocusableInTouchMode = false
            inputType = InputType.TYPE_NULL
            isClickable = true
            setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, icSwap.drawable, null)
            setOnTouchListener(departureTouchListener)
            compoundDrawablePadding = resources.getDimension(R.dimen.sr3_et_compound_drawable_p).toInt()
        }
    }
    val arrivalEditText: EditText by lazy {
        val hint = resources.getString(R.string.arrival_hint)
        EditText(context).applySettings(hint).apply {
            isFocusableInTouchMode = false
            inputType = InputType.TYPE_NULL
            isClickable = true
            setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, icClear.drawable, null)
            compoundDrawablePadding = resources.getDimension(R.dimen.sr3_et_compound_drawable_p).toInt()
            setOnTouchListener(arrivalTouchListener)
            id = R.id.et_arrival
        }
    }

    var baseColor = 0
        set(value) {
            basePaint.color = value
            if (field != value && field != 0) invalidate()
            field = value
        }

    var lineColor = 0
        set(value) {
            linePaint.color = value
            if (field != value && field != 0) invalidate()
            field = value
        }

    var icSwapColor = 0
        set(value) {
            icSwap.setColorFilter(value)
            if (field != value && field != 0) invalidate()
            field = value
        }

    var icBackColor = 0
        set(value) {
            icBack.setColorFilter(value)
            if (field != value && field != 0) invalidate()
            field = value
        }

    var icClearColor = 0
        set(value) {
            icClear.setColorFilter(value)
            if (field != value && field != 0) invalidate()
            field = value
        }

    init {
        context.withStyledAttributes(attrs, R.styleable.SearchRoutLayout, R.attr.searchRoutViewStyle) {
            baseColor = getColor(
                R.styleable.SearchRoutLayout_baseColor,
                ContextCompat.getColor(context, ru.tk4dmitriy.core.res.R.color.grey_3)
            )
            lineColor = getColor(
                R.styleable.SearchRoutLayout_lineColor,
                ContextCompat.getColor(context, ru.tk4dmitriy.core.res.R.color.grey_4)
            )
            icSwapColor = getColor(
                R.styleable.SearchRoutLayout_icSwapColor,
                ContextCompat.getColor(context, ru.tk4dmitriy.core.res.R.color.white)
            )
            icBackColor = getColor(
                R.styleable.SearchRoutLayout_icBackColor,
                ContextCompat.getColor(context, ru.tk4dmitriy.core.res.R.color.white)
            )
            icClearColor = getColor(
                R.styleable.SearchRoutLayout_icClearColor,
                ContextCompat.getColor(context, ru.tk4dmitriy.core.res.R.color.grey_6)
            )
        }

        addView(icBack)
        addView(departureEditText)
        addView(arrivalEditText)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSpec = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        measureChild(icBack, widthMeasureSpec, heightMeasureSpec)
        measureChild(departureEditText, widthMeasureSpec, heightMeasureSpec)

        val desiredEditTextWidth = run {
            val marginLeft = resources.getDimensionPixelSize(R.dimen.sr3_et_m_left)
            val marginRight = resources.getDimensionPixelSize(R.dimen.sr3_et_m_right)
            widthSpec - paddingLeft - paddingRight - marginLeft- marginRight
        }

        departureEditText.measure(
            MeasureSpec.makeMeasureSpec(desiredEditTextWidth, MeasureSpec.EXACTLY),
            heightMeasureSpec
        )
        arrivalEditText.measure(
            MeasureSpec.makeMeasureSpec(desiredEditTextWidth, MeasureSpec.EXACTLY),
            heightMeasureSpec
        )

        val desiredWidth = run {
            val maxWidth = maxOf(departureEditText.measuredWidth, arrivalEditText.measuredWidth)
            paddingLeft + paddingRight + maxWidth
        }
        val desiredHeight = run {
            val marginTop = resources.getDimension(R.dimen.sr3_et_m_top).toInt()
            val marginBottom = resources.getDimension(R.dimen.sr3_et_m_bottom).toInt()
            paddingTop + paddingBottom + marginTop + marginBottom +
                    departureEditText.measuredHeight + arrivalEditText.measuredHeight
        }

        val measuredWidth = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSpec
            MeasureSpec.AT_MOST -> minOf(widthSpec, desiredWidth)
            else -> desiredWidth
        }
        val measuredHeight = when (MeasureSpec.getMode(heightMeasureSpec)) {
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(heightMeasureSpec)
            MeasureSpec.AT_MOST -> minOf(MeasureSpec.getSize(heightMeasureSpec), desiredHeight)
            else -> desiredHeight
        }

        setMeasuredDimension(measuredWidth, measuredHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        backImageLayout()
        departureEditTextLayout()
        arrivalEditTextLayout()
    }

    private fun backImageLayout() {
        val left = resources.getDimension(R.dimen.sr3_iv_back_m_left).toInt() + paddingLeft
        val top = (height + paddingTop - paddingBottom) / 2 - icBack.measuredHeight / 2
        val right = left + icBack.measuredWidth
        val bottom = top + icBack.measuredHeight
        icBack.layout(left, top, right, bottom)
    }

    override fun onSaveInstanceState(): Parcelable {
        return SavedState(super.onSaveInstanceState()).apply {
            baseColor = this@SearchRoute3Layout.baseColor
            lineColor = this@SearchRoute3Layout.lineColor
            icSwapColor = this@SearchRoute3Layout.icSwapColor
            icBackColor = this@SearchRoute3Layout.icBackColor
            icClearColor = this@SearchRoute3Layout.icClearColor
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            baseColor = state.baseColor
            lineColor = state.lineColor
            icSwapColor = state.icSwapColor
            icBackColor = state.icBackColor
            icClearColor = state.icClearColor
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    private fun departureEditTextLayout() {
        val left = resources.getDimension(R.dimen.sr3_et_m_left).toInt() + paddingLeft
        val top = (height + paddingTop - paddingBottom) / 2 - departureEditText.measuredHeight
        val right = left + departureEditText.measuredWidth
        val bottom = top + departureEditText.measuredHeight
        departureEditText.layout(left, top, right, bottom)
    }

    private fun arrivalEditTextLayout() {
        val left = resources.getDimension(R.dimen.sr3_et_m_left).toInt() + paddingLeft
        val top = (height + paddingTop - paddingBottom) / 2
        val right = left + arrivalEditText.measuredWidth
        val bottom = top + arrivalEditText.measuredHeight
        arrivalEditText.layout(left, top, right, bottom)
    }

    override fun dispatchDraw(canvas: Canvas) {
        baseDraw(canvas)
        lineDraw(canvas)
        super.dispatchDraw(canvas)
    }

    private fun baseDraw(canvas: Canvas) {
        val left = paddingLeft.toFloat()
        val top = paddingTop.toFloat()
        val right = width.toFloat() - paddingRight
        val bottom = height.toFloat() - paddingBottom
        canvas.drawRect(left, top, right, bottom, basePaint)
    }

    private fun lineDraw(canvas: Canvas) {
        val left = resources.getDimension(R.dimen.sr3_line_m_left) + paddingLeft
        val top = (height + paddingTop - paddingBottom) / 2 - resources.getDimension(R.dimen.sr3_line_height) / 2
        val right = width - resources.getDimension(R.dimen.sr3_line_m_right) - paddingRight
        val bottom = top + resources.getDimension(R.dimen.sr3_line_height)
        canvas.drawRect(left, top, right, bottom, linePaint)
    }

    private fun EditText.applySettings(hint: String) = apply {
        val hintTextColor = ContextCompat.getColor(context, ru.tk4dmitriy.core.res.R.color.grey_6)
        val spannableString = SpannableString(hint)
        spannableString.setSpan(
            ForegroundColorSpan(hintTextColor),
            0,
            hint.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        this.hint = spannableString
        isSingleLine = true
        background = null
        setTextAppearance(ru.tk4dmitriy.core.res.R.style.ButtonText1)
        setTextColor(ContextCompat.getColor(context, ru.tk4dmitriy.core.res.R.color.white))
    }

    private class SavedState : BaseSavedState {
        var baseColor = 0
        var lineColor = 0
        var icSwapColor = 0
        var icBackColor = 0
        var icClearColor = 0
        var departureEditTextId: Int = View.NO_ID

        constructor(superState: Parcelable?) : super(superState)

        constructor(parcel: Parcel) : super(parcel) {
            baseColor = parcel.readInt()
            lineColor = parcel.readInt()
            icSwapColor = parcel.readInt()
            icBackColor = parcel.readInt()
            departureEditTextId = parcel.readInt()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(baseColor)
            out.writeInt(lineColor)
            out.writeInt(icSwapColor)
            out.writeInt(icBackColor)
            out.writeInt(icClearColor)
            out.writeInt(departureEditTextId)
        }

        companion object CREATOR : Parcelable.Creator<SavedState> {
            override fun createFromParcel(parcel: Parcel): SavedState {
                return SavedState(parcel)
            }

            override fun newArray(size: Int): Array<SavedState?> {
                return arrayOfNulls(size)
            }
        }
    }
}