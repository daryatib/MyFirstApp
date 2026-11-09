package com.example.myfirstapp.data.applist

import com.example.myfirstapp.domain.applist.AppListItem

fun AppListItemDto.toDomain(): AppListItem {
    return AppListItem(
        id = this.id,
        name = this.name,
        description = this.description,
        category = this.category,
        iconUrl = this.iconUrl
    )
}