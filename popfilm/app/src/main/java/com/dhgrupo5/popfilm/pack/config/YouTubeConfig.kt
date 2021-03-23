package com.dhgrupo5.popfilm.config

abstract class YouTubeConfig {

    abstract class Key {
        companion object {

            const val GOOGLE_DEV = "AIzaSyB_Sh_3krRG3AqcBRY4uMl97Cjmm4YjXWk"
        }
    }

    abstract class Channel {
        companion object {

            const val CHANNEL_ID = "UCvC4D8onUfXzvjTOM-dBfEA"


            const val CHANNEL_URL = "https://www.youtube.com/channel/$CHANNEL_ID"


            const val VIDEO_URL_TEMPLATE = "https://www.youtube.com/watch?v=lZ7etQWylBg&list=PLK5HARgNfgj-XjGkMVcqEGp2QJmPaVxOr&index=2"
            const val VIDEO_THUMB_URL_TEMPLATE = "https://www.youtube.com/playlist?list=PLK5HARgNfgj9Pje1Tek5xv29EiKloPoNR"
            const val PLAYLIST_URL_TEMPLATE = "https://www.youtube.com/c/marvel/playlists"
        }
    }

    abstract class ApiV3 {
        companion object {

            const val BASE_URL = "https://www.googleapis.com/"


            const val VIDEO_PATH = "youtube/v3/search"
            const val PLAYLISTS_PATH = "youtube.com/c/marvel/playlists"

            const val PART_PARAM = "snippet"
            const val MAX_RESULTS_VIDEO_PARAM = "1"
            const val MAX_RESULTS_PLAYLISTS_PARAM = "500"
            const val ORDER_PARAM = "date"
        }
    }

    abstract class Notification {
        companion object {

            const val ALTERNATIVE_URL = "https://www.youtube.com/c/marvel/videos"
            const val VIDEO_PARAM = "v"
            const val EMPTY = ""
        }
    }
}





