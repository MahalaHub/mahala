package semir.mahovkic.mahalahub.data.network.model

data class LoginRequestDto(
    val username: String,
    val emailOrPhoneNumber: String
)
