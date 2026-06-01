package com.metra.nbaintegra.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NbaPrimaryButton(
    text: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    NbaButton(
        onClick = onClick,
        modifier = modifier,
        text = text,
        enabled = enabled,
        border = null,
        colors = primaryColors(),
    )
}

@Composable
fun NbaSecondaryButton(
    text: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    NbaButton(
        onClick = onClick,
        modifier = modifier,
        text = text,
        enabled = enabled,
        border = BorderStroke(2.dp, NbaColors.black),
        colors = secondaryColors(),
    )
}

@Composable
fun NbaActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    NbaButton(
        onClick = onClick,
        modifier = modifier,
        text = text,
        enabled = enabled,
        colors = actionColors(),
        border = null,
    )
}

@Composable
private fun NbaButton(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String?,
    enabled: Boolean,
    border: BorderStroke?,
    colors: ButtonColors,
    fullWidth: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        contentPadding = PaddingValues(0.dp),
        border = border,
        colors = colors,
        modifier =
            modifier
                .fillMaxWidth(if (fullWidth) 1f else 0.6f)
                .requiredHeight(40.dp),
        shape = CircleShape,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            text?.let {
                NbaText(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = if (enabled) NbaColors.black else NbaColors.chrome300,
                    fontSize = 18.sp,
                )
            }
        }
    }
}

@Composable
private fun primaryColors(): ButtonColors =
    ButtonDefaults.buttonColors(
        containerColor = NbaColors.black,
        contentColor = NbaColors.white,
        disabledContainerColor = NbaColors.chrome700,
        disabledContentColor = NbaColors.chrome300,
    )

@Composable
private fun secondaryColors(): ButtonColors =
    ButtonDefaults.buttonColors(
        containerColor = NbaColors.white,
        contentColor = NbaColors.black,
        disabledContainerColor = NbaColors.chrome700,
        disabledContentColor = NbaColors.chrome300,
    )

@Composable
private fun actionColors(): ButtonColors =
    ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = NbaColors.black,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = NbaColors.chrome300,
    )
