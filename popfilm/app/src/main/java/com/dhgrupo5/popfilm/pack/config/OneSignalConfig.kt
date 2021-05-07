package com.dhgrupo5.popfilm.config


abstract class OneSignalConfig {

    abstract class Firebase {
        companion object CloudMessage {

            const val SERVER_KEY = "AAAANOC1oXw:APA91bHA8sV9Hkg18fUF8EtaK6uQEQ0lySgWvewqWS6zfzk1oXLTFAmkCH6LcIc8Ru-fnYNTTj7aXRAb3r9GMDLJIk_PVNEcfvVWpGKa3HKow2OINHex3rbVTBEfrLPRp05pTvVq6dvL"
            const val SENDER_ID = ""
        }
    }

    abstract class App {
        companion object {

            const val ID = ""
        }
    }

    abstract class Notification {
        companion object Parameter {

            const val VIDEO = "video"
            const val TITLE = "title"
            const val DESCRIPTION = "description"


            const val EMPTY = ""
        }
    }
}