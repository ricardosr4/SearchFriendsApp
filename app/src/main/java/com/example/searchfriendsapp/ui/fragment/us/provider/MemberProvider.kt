package com.example.searchfriendsapp.ui.fragment.us.provider

import com.example.searchfriendsapp.data.response.MembersResponse

class MemberProvider {
    companion object {
        fun getMember(): List<MembersResponse> {
            return listOf(
                MembersResponse(

                    image = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQbxa7KczTYeibkI7asnkTcyXK3JjO-AKcY3Qn3lZg1SPmrBV82Y39wbjpdpwu9gTyGzlkKQeYHDK_7GEtVpB-h2jN9vYgdaGypq2-Zng"
                ),
                MembersResponse(
                    image = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQbxa7KczTYeibkI7asnkTcyXK3JjO-AKcY3Qn3lZg1SPmrBV82Y39wbjpdpwu9gTyGzlkKQeYHDK_7GEtVpB-h2jN9vYgdaGypq2-Zng"
                )
            )

        }
    }
}