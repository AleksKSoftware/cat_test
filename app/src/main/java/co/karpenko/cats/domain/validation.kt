package co.karpenko.cats.domain

import java.util.regex.Pattern

// A placeholder email validation check
val String.isEmailValid: Boolean
    get() = Pattern.matches(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+",
        this
    )

val String.isUserPasswordValid: Boolean
    get() = length >= USER_PASSWORD_COUNT_LIMIT


private const val USER_PASSWORD_COUNT_LIMIT = 6
