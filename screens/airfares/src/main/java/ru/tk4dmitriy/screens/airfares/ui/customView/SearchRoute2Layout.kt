package ru.tk4dmitriy.screens.airfares.ui.customView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import android.text.InputType
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
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
import ru.tk4dmitriy.core.utils.Utils.filterText
import ru.tk4dmitriy.screens.airfares.R


@SuppressLint("ClickableViewAccessibility")
internal class SearchRoute2Layout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): ViewGroup(context, attrs) {
    private val basePaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            val cornerRadius = resources.getDimension(R.dimen.sr2_base_corner_radius)
            pathEffect = CornerPathEffect(cornerRadius)
        }
    }
    private val linePaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    private val icSearch: ImageView by lazy {
        ImageView(context).apply {
            setImageResource(R.drawable.ic_search)
        }
    }

    private val icAirplane: ImageView by lazy {
        ImageView(context).apply {
            setImageResource(R.drawable.ic_airplane2)
        }
    }

    private val icClear: ImageView by lazy {
        ImageView(context).apply {
            setImageResource(R.drawable.ic_cancel)
        }
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

    private val EditText.textChangeListener: TextWatcher
        get() = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    val filteredText = it.toString().filterText()
                    if (it.toString() != filteredText) {
                        setText(filteredText)
                        setSelection(filteredText.length)
                    }
                }
            }
        }

    val departureEditText: EditText by lazy {
        val hint = resources.getString(R.string.departure_hint)
        EditText(context).applySettings(hint).apply {
            isFocusableInTouchMode = false
            inputType = InputType.TYPE_NULL
            isClickable = true
            setCompoundDrawablesRelativeWithIntrinsicBounds(icAirplane.drawable, null, null, null)
            compoundDrawablePadding = resources.getDimension(R.dimen.sr2_et_compound_drawable_p).toInt()
        }
    }
    val arrivalEditText: EditText by lazy {
        val hint = resources.getString(R.string.arrival_hint)
        EditText(context).applySettings(hint).apply {
            addTextChangedListener(textChangeListener)
            setCompoundDrawablesRelativeWithIntrinsicBounds(icSearch.drawable, null, icClear.drawable, null)
            compoundDrawablePadding = resources.getDimension(R.dimen.sr2_et_compound_drawable_p).toInt()
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

    var icSearchColor = 0
        set(value) {
            icSearch.setColorFilter(value)
            if (field != value && field != 0) invalidate()
            field = value
        }

    var icAirplaneColor = 0
        set(value) {
            icAirplane.setColorFilter(value)
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
                ContextCompat.getColor(context, ru.tk4dmitriy.core.res.R.color.grey_5)
            )
            icSearchColor = getColor(
                R.styleable.SearchRoutLayout_icSearchColor,
                ContextCompat.getColor(context, ru.tk4dmitriy.core.res.R.color.white)
            )
            icAirplaneColor = getColor(
                R.styleable.SearchRoutLayout_icAirplaneColor,
                ContextCompat.getColor(context, ru.tk4dmitriy.core.res.R.color.grey_6)
            )
            icClearColor = getColor(
                R.styleable.SearchRoutLayout_icClearColor,
                ContextCompat.getColor(context, ru.tk4dmitriy.core.res.R.color.grey_6)
            )
        }

        addView(departureEditText)
        addView(arrivalEditText)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSpec = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        measureChild(departureEditText, widthMeasureSpec, heightMeasureSpec)

        val desiredEditTextWidth = run {
            val marginLeft = resources.getDimensionPixelSize(R.dimen.sr2_et_m_left)
            val marginRight = resources.getDimensionPixelSize(R.dimen.sr2_et_m_right)
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
            val marginTop = resources.getDimension(R.dimen.sr2_et_m_top).toInt()
            val marginBottom = resources.getDimension(R.dimen.sr2_et_m_bottom).toInt()
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
        departureEditTextLayout()
        arrivalEditTextLayout()
    }

    override fun onSaveInstanceState(): Parcelable {
        return SavedState(super.onSaveInstanceState()).apply {
            baseColor = this@SearchRoute2Layout.baseColor
            lineColor = this@SearchRoute2Layout.lineColor
            icSearchColor = this@SearchRoute2Layout.icSearchColor
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            baseColor = state.baseColor
            lineColor = state.lineColor
            icSearchColor = state.icSearchColor
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    private fun departureEditTextLayout() {
        val left = resources.getDimension(R.dimen.sr2_et_m_left).toInt() + paddingLeft
        val top = (height + paddingTop - paddingBottom) / 2 - departureEditText.measuredHeight
        val right = left + departureEditText.measuredWidth
        val bottom = top + departureEditText.measuredHeight
        departureEditText.layout(left, top, right, bottom)
    }

    private fun arrivalEditTextLayout() {
        val left = resources.getDimension(R.dimen.sr2_et_m_left).toInt() + paddingLeft
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
        val left = resources.getDimension(R.dimen.sr2_line_m_left) + paddingLeft
        val top = (height + paddingTop - paddingBottom) / 2 - resources.getDimension(R.dimen.sr2_line_height) / 2
        val right = width - resources.getDimension(R.dimen.sr2_line_m_right) - paddingRight
        val bottom = top + resources.getDimension(R.dimen.sr2_line_height)
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
        var icSearchColor = 0
        var departureEditTextId: Int = View.NO_ID

        constructor(superState: Parcelable?) : super(superState)

        constructor(parcel: Parcel) : super(parcel) {
            baseColor = parcel.readInt()
            lineColor = parcel.readInt()
            icSearchColor = parcel.readInt()
            departureEditTextId = parcel.readInt()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(baseColor)
            out.writeInt(lineColor)
            out.writeInt(icSearchColor)
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