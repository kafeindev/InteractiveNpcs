package dev.kafein.interactivenpcs.conversation;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.Map;

public final class DialogueMap {
    private static final Comparator<Dialogue> COMPARATOR = Comparator.comparingInt(Dialogue::getOrder);

    private final Map<String, Dialogue> dialogueMap;

    public DialogueMap() {
        this(Maps.newHashMap());
    }

    public DialogueMap(Map<String, Dialogue> dialogueMap) {
        this.dialogueMap = dialogueMap;
    }

    public static DialogueMap empty() {
        return new DialogueMap();
    }

    public static DialogueMap of(Dialogue... dialogues) {
        Map<String, Dialogue> dialogueMap = Maps.newHashMap();
        for (Dialogue dialogue : dialogues) {
            dialogueMap.put(dialogue.getId(), dialogue);
        }

        return new DialogueMap(dialogueMap);
    }

    public static DialogueMap of(Map<String, Dialogue> dialogueMap) {
        return new DialogueMap(dialogueMap);
    }

    public Map<String, Dialogue> getDialogueMap() {
        return this.dialogueMap;
    }

    public Map<String, Dialogue> getDialogueSortedMap() {
        Map<String, Dialogue> sortedMap = Maps.newLinkedHashMap();
        this.dialogueMap.values().stream()
                .sorted(COMPARATOR)
                .forEach(dialogue -> sortedMap.put(dialogue.getId(), dialogue));

        return sortedMap;
    }

    public @Nullable Dialogue get(String id) {
        return this.dialogueMap.get(id);
    }

    public void put(Dialogue dialogue) {
        this.dialogueMap.put(dialogue.getId(), dialogue);
    }

    public void remove(String id) {
        this.dialogueMap.remove(id);
    }
}
