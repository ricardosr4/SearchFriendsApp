package com.example.searchfriendsapp.ui.fragment.us.provider

import com.example.searchfriendsapp.data.response.UsResponse

class MemberProvider {
    companion object {
        fun getMember(): List<UsResponse> {
            return listOf(
                UsResponse(
                    //image = "https://z-p3-scontent.fscl25-1.fna.fbcdn.net/v/t39.30808-6/319752281_1226513101408919_6415640321846121098_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=a5f93a&_nc_eui2=AeF4nalr1jLGViwA5XxorAVG2rP0boV95oDas_RuhX3mgOXyIBdSfPEN8x-9zl0wjwnuLibF-y-aEVTvbfLaX_Rf&_nc_ohc=A3mY2S_eAhEQ7kNvgFBNLwv&_nc_zt=23&_nc_ht=z-p3-scontent.fscl25-1.fna&_nc_gid=AY_jTOwehqYUUK1iY80qlz7&oh=00_AYD-nAoqhoCxW58Ui6tJr0fp1Ti3PBc0sRpbyQJn18SSkA&oe=66FE8762"
                    image = "https://media.licdn.com/dms/image/v2/D4E35AQFnfE0TgYwgoQ/profile-framedphoto-shrink_800_800/profile-framedphoto-shrink_800_800/0/1726934023117?e=1728662400&v=beta&t=k7jlXgD4men_52J9x3UFIxZkYJZO28JecsF2IqLxpFY"
                ),
                UsResponse(
                    //image = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQbxa7KczTYeibkI7asnkTcyXK3JjO-AKcY3Qn3lZg1SPmrBV82Y39wbjpdpwu9gTyGzlkKQeYHDK_7GEtVpB-h2jN9vYgdaGypq2-Zng"
                    image = "https://i.pinimg.com/736x/3b/66/df/3b66df81228a4d34282ee6ff1f1723f3.jpg"
                )
            )
        }
    }
}