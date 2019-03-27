package furhatos.app.actor_theater.nlu

import furhatos.nlu.Intent
import furhatos.util.Language



class Section1Intent() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        // We mark "pizza" here as required, since it is not enough with any of the other words to trigger this intent
        return listOf("First section")
    }

}
class Section2Intent() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        // We mark "pizza" here as required, since it is not enough with any of the other words to trigger this intent
        return listOf("Second section")
    }

}
