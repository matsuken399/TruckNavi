package com.websarva.wings.android.trucknavi

import android.provider.BaseColumns

object DBContract {

    /* Inner class that defines the table contents */
    class UserEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "users"
            val COLUMN_DATE = "date"
            val COLUMN_COURSE = "course"
            val COLUMN_No = "No"
            val COLUMN_NAME = "name"
            val COLUMN_LATLNG = "latlng"
        }
    }
}
