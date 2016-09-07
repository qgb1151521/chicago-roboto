package com.gdgchicagowest.windycitydevcon.features.speakerlist

import dagger.Subcomponent

@Subcomponent(modules = arrayOf(SpeakerListModule::class))
interface SpeakerListComponent {
    fun inject(speakerListView: SpeakerListView)
}