package br.com.eduardo.martins.githubrepositories.domain

/**
 * Interface for a Interactor from Clean Architecture.
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract)
 */

interface Interactor<T, Params> {

    suspend fun execute(params: Params): T

}