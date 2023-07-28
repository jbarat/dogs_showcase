package hu.jozsefbarat.domain.common

interface UseCase<Response> {
    suspend fun execute(): Response
}

interface UseCaseWithParams<Response, Params> {
    suspend fun execute(params: Params): Response
}

