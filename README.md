# Architectury Template

This template adheres to semantic versioning. 
Here is a list of things that should be changed when using this template initially. You can use this as a checklist.

Any mention of `architecturymod` should be replaced with the modid. Here is a list of files which will need changing in some way:
- settings.gradle - Change rootProject.name to be the displayed name of the mod in IDEA.
- gradle.properties - Change archives_name and maven_group.
- architecturymod.accesswidener - Change file name.
- architectury.common.json - Change string inside to be name of the access widener.
- src/main/java/net/architecturymod - Change this folder name to be the modid.
- ArchitecturyMod.java - Rename class to the mod's name and change modid to match `archives_name` in `gradle.properties`. Do the same for ArchitecturyModForge and ArchitecturyModFabric.
- architecturymod-common.mixins.json - Change filename and package to match new file structure.
- architecturymod-fabric.mixins.json - Change filename and package to match new file structure.
- architecturymod-forge.mixins.json - Change filename and package to match new file structure.
- fabric.mod.json - Change fields inside file. id must match `archives_name` in `gradle.properties`. Mixins and entrypoints must also reflect the folder structure defined.
- mods.toml - Change fields inside file. Match to `fabric.mod.json` on similar things. You do not need to specify mixin files or entrypoints here.
- pack.mcmeta - Change description to mod display name.
