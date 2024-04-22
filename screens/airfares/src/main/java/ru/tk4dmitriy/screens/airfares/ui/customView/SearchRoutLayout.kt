package ru.tk4dmitriy.screens.airfares.ui.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.os.Parcel
import android.os.Parcelable
import android.text.InputFilter
import android.text.InputType
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import ru.tk4dmitriy.screens.airfares.R
import ru.tk4dmitriy.core.res.R as CoreResR


class SearchRoutLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): ViewGroup(context, attrs) {
    private val basePaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            val cornerRadius = resources.getDimension(R.dimen.base_corner_radius)
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

    val departureEditText: EditText by lazy {
        EditText(context).applySettings(resources.getString(R.string.departure_hint)).apply {
            allowOnlyCyrillic()
            id = R.id.et_departure
        }
    }
    val arrivalEditText: EditText by lazy {
        EditText(context).applySettings(resources.getString(R.string.arrival_hint)).apply {
            isFocusableInTouchMode = false
            inputType = InputType.TYPE_NULL
            isClickable = true
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

    init {
        context.withStyledAttributes(attrs, R.styleable.SearchRoutLayout, R.attr.searchRoutViewStyle) {
            baseColor = getColor(
                R.styleable.SearchRoutLayout_baseColor,
                ContextCompat.getColor(context, CoreResR.color.grey_4)
            )
            lineColor = getColor(
                R.styleable.SearchRoutLayout_lineColor,
                ContextCompat.getColor(context, CoreResR.color.grey_5)
            )
            icSearchColor = getColor(
                R.styleable.SearchRoutLayout_icSearchColor,
                ContextCompat.getColor(context, CoreResR.color.white)
            )
        }

        addView(icSearch)
        addView(departureEditText)
        addView(arrivalEditText)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSpec = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        measureChild(icSearch, widthMeasureSpec, heightMeasureSpec)
        measureChild(departureEditText, widthMeasureSpec, heightMeasureSpec)

        val desiredEditTextWidth =
            widthSpec - paddingLeft - paddingRight - icSearch.measuredWidth -
                    resources.getDimensionPixelSize(R.dimen.m_right_edit_text)

        departureEditText.measure(
            MeasureSpec.makeMeasureSpec(desiredEditTextWidth, MeasureSpec.EXACTLY),
            heightMeasureSpec
        )

        arrivalEditText.measure(
            MeasureSpec.makeMeasureSpec(desiredEditTextWidth, MeasureSpec.EXACTLY),
            heightMeasureSpec
        )

        val desiredWidth = paddingLeft + paddingRight + icSearch.measuredWidth + maxOf(
            departureEditText.measuredWidth, arrivalEditText.measuredWidth
        )
        val desiredHeight = paddingTop + paddingBottom +
                departureEditText.measuredHeight + arrivalEditText.measuredHeight

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
        searchImageLayout()
        departureEditTextLayout()
        arrivalEditTextLayout()
    }

    private fun searchImageLayout() {
        val left = resources.getDimension(R.dimen.m_left_search_image).toInt() + paddingLeft
        val top = ((height + paddingTop - paddingBottom) / 2 - icSearch.measuredHeight / 2)
        val right = left + icSearch.measuredWidth
        val bottom = top + icSearch.measuredHeight
        icSearch.layout(left, top, right, bottom)
    }

    private fun departureEditTextLayout() {
        val left = resources.getDimension(R.dimen.m_left_edit_text).toInt() + paddingLeft
        val top = paddingTop
        val right = left + departureEditText.measuredWidth
        val bottom = top + departureEditText.measuredHeight
        departureEditText.layout(left, top, right, bottom)
    }

    private fun arrivalEditTextLayout() {
        val left = resources.getDimension(R.dimen.m_left_edit_text).toInt() + paddingLeft
        val top = paddingTop + departureEditText.measuredHeight
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
        val left = resources.getDimension(R.dimen.m_left_line) + paddingLeft
        val top = (height + paddingTop - paddingBottom) / 2 - resources.getDimension(R.dimen.height_line) / 2
        val right = width - resources.getDimension(R.dimen.m_right_line) - paddingRight
        val bottom = top + resources.getDimension(R.dimen.height_line)
        canvas.drawRect(left, top, right, bottom, linePaint)
    }

    private fun EditText.applySettings(hint: String) = apply {
        val hintTextColor = ContextCompat.getColor(context, CoreResR.color.grey_6)
        val spannableString = SpannableString(hint)
        spannableString.setSpan(ForegroundColorSpan(hintTextColor), 0, hint.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        this.hint = spannableString
        isSingleLine = true
        background = null
        setTextAppearance(CoreResR.style.ButtonText1)
        setTextColor(ContextCompat.getColor(context, CoreResR.color.white))
    }

    override fun onSaveInstanceState(): Parcelable {
        return SavedState(super.onSaveInstanceState()).apply {
            baseColor = this@SearchRoutLayout.baseColor
            lineColor = this@SearchRoutLayout.lineColor
            icSearchColor = this@SearchRoutLayout.icSearchColor
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


    private fun EditText.allowOnlyCyrillic() {
        val filter = object : InputFilter {
            override fun filter(
                source: CharSequence?,
                start: Int,
                end: Int,
                dest: Spanned?,
                dstart: Int,
                dend: Int
            ): CharSequence? {
                for (i in start until end)
                    if (!Character.UnicodeBlock.of(source!![i])?.equals(Character.UnicodeBlock.CYRILLIC)!!)
                        return ""
                return null
            }
        }
        this.filters = arrayOf(filter)
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