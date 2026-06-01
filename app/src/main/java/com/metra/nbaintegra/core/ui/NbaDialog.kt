package com.metra.nbaintegra.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.metra.nbaintegra.core.ui.NbaColors.black
import com.metra.nbaintegra.core.ui.NbaColors.chrome900
import com.metra.nbaintegra.core.ui.NbaColors.white

@Composable
fun NbaAlertDialog(
    title: String,
    onDismiss: () -> Unit,
    positiveButtonText: String,
    modifier: Modifier = Modifier,
    onPositiveButtonClick: () -> Unit = onDismiss,
    message: String? = null,
    negativeButtonText: String? = null,
    onNegativeButtonClick: (() -> Unit) = onDismiss,
    neutralButtonText: String? = null,
    onNeutralButtonClick: (() -> Unit) = onDismiss,
    dialogProperties: DialogProperties = DialogProperties(),
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = dialogProperties,
    ) {
        Surface(
            modifier = modifier,
            shape = RoundedCornerShape(16.dp),
            color = white,
            contentColor = chrome900,
        ) {
            Column(
                modifier = Modifier.padding(NbaDimensions.sizeXS),
            ) {
                NbaText(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier =
                        Modifier
                            .padding(NbaDimensions.sizeS),
                )
                if (!message.isNullOrEmpty()) {
                    NbaText(
                        text = message,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(NbaDimensions.sizeS),
                    )
                }
                Spacer(modifier = Modifier.height(NbaDimensions.sizeXS))
                Row {
                    neutralButtonText?.let {
                        NbaActionButton(
                            text = it,
                            onClick = onNeutralButtonClick,
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    negativeButtonText?.let {
                        NbaActionButton(
                            text = it,
                            onClick = onNegativeButtonClick,
                        )
                    }
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(start = NbaDimensions.sizeXXS),
                        contentAlignment = BottomEnd,
                    ) {
                        NbaText(
                            text = positiveButtonText,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier =
                                Modifier
                                    .clickable { onPositiveButtonClick() }
                                    .padding(end = NbaDimensions.sizeXS),
                            color = black,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(NbaDimensions.sizeXXS))
            }
        }
    }
}
