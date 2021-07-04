package br.com.eduardo.martins.githubrepositories.domain.exception

import java.lang.Exception


abstract class MyException : Exception {

    override val message: String
        get() = super.message ?: ""

    constructor(message: String): super(message)
    constructor(message: String, cause: Throwable): super(message, cause)

}