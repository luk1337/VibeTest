package com.luk.vibetest

import android.os.*
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    internal enum class Effect {
        /**
         * A single click effect.
         *
         * This effect should produce a sharp, crisp click sensation.
         */
        CLICK,

        /**
         * A double click effect.
         *
         * This effect should produce two sequential sharp, crisp click sensations with a minimal
         * amount of time between them.
         */
        DOUBLE_CLICK,

        /**
         * A tick effect.
         *
         * This effect should produce a soft, short sensation, like the tick of a clock.
         */
        TICK,

        /**
         * A thud effect.
         *
         * This effect should solid feeling bump, like the depression of a heavy mechanical button.
         */
        THUD,

        /**
         * A pop effect.
         *
         * A short, quick burst effect.
         */
        POP,

        /**
         * A heavy click effect.
         *
         * This should produce a sharp striking sensation, like a click but stronger.
         */
        HEAVY_CLICK,

        /**
         * Ringtone patterns. They may correspond with the device's ringtone audio, or may just be a
         * pattern that can be played as a ringtone with any audio, depending on the device.
         */
        RINGTONE_1, RINGTONE_2, RINGTONE_3, RINGTONE_4, RINGTONE_5, RINGTONE_6, RINGTONE_7, RINGTONE_8, RINGTONE_9, RINGTONE_10, RINGTONE_11, RINGTONE_12, RINGTONE_13, RINGTONE_14, RINGTONE_15,

        /**
         * A soft tick effect meant to be played as a texture.
         *
         * A soft, short sensation like the tick of a clock. Unlike regular effects, texture effects
         * are expected to be played multiple times in quick succession, replicating a specific
         * texture to the user as a form of haptic feedback.
         */
        TEXTURE_TICK
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 10L..150L step 10L) {
            val button = Button(this)
            button.text = "${i}ms"
            button.setOnClickListener { _ ->
                val vib = getSystemService(Vibrator::class.java)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    vib.vibrate(
                        VibrationEffect.createOneShot(i, VibrationEffect.DEFAULT_AMPLITUDE),
                        VibrationAttributes.createForUsage(VibrationAttributes.USAGE_MEDIA)
                    )
                } else {
                    vib.vibrate(VibrationEffect.createOneShot(i, VibrationEffect.DEFAULT_AMPLITUDE))
                }
            }

            val layout = findViewById<View>(R.id.layout) as LinearLayout
            layout.addView(
                button,
                FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )
            )
        }

        enumValues<Effect>().forEach {
            val button = Button(this)
            button.text = it.name
            button.setOnClickListener { _ ->
                val vib = getSystemService(Vibrator::class.java)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    vib.vibrate(
                        VibrationEffect.createPredefined(it.ordinal),
                        VibrationAttributes.createForUsage(VibrationAttributes.USAGE_MEDIA)
                    )
                } else {
                    vib.vibrate(VibrationEffect.createPredefined(it.ordinal))
                }
            }

            val layout = findViewById<View>(R.id.layout) as LinearLayout
            layout.addView(
                button,
                FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )
            )
        }
    }
}