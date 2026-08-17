---
navigation:
  title: Junction
  icon: "vintagetech:junction"
  parent: motion.md
categories:
  - transfer
item_ids:
  - vintagetech:junction
position: 1
---

# Junction

An junction carries rotational motion and rotation direction from a generator to any attached consumers based on any axis

<GameScene zoom="3" interactive={true}>
  <Block x="0" y="0" z="0" id="vintagetech:junction"  p:enabled="true" p:inverted="false"/>

  <Block x="1" y="0" z="0" id="vintagetech:axle" p:axis="x" p:enabled="true" p:has_rope="false" p:inverted="false"/>

  <Block x="-1" y="0" z="0" id="vintagetech:axle" p:axis="x" p:enabled="true" p:has_rope="false" p:inverted="false"/>

  <Block x="0" y="0" z="1" id="vintagetech:axle" p:axis="z" p:enabled="true" p:has_rope="false" p:inverted="false"/>

  <Block x="0" y="0" z="-1" id="vintagetech:axle" p:axis="z" p:enabled="true" p:has_rope="false" p:inverted="false"/>

  <Block x="0" y="1" z="0" id="vintagetech:axle" p:axis="y" p:enabled="true" p:has_rope="false" p:inverted="false"/>

  <Block x="0" y="-1" z="0" id="vintagetech:axle" p:axis="y" p:enabled="true" p:has_rope="false" p:inverted="false"/>
</GameScene>

<RecipeFor id="vintagetech:junction" />