package com.zacharee1.systemuituner.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.zacharee1.systemuituner.util.SettingsType
import com.zacharee1.systemuituner.util.writeSetting
import kotlinx.android.synthetic.main.dialog_write_setting.view.*

class WriteSettingsView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    override fun onFinishInflate() {
        super.onFinishInflate()

        apply.setOnClickListener {
            val key = key_entry.text?.toString()
            val value = value_entry.text?.toString()
            val type = SettingsType.fromString(settings_type.selectedItem.toString())

            if (!key.isNullOrBlank()) {
                context.writeSetting(type, key, value)
            }
        }
    }
}