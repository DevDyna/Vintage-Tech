---
navigation:
  title: Fan
  icon: "vintagetech:fan"
  parent: motion.md
categories:
  - consumer
item_ids:
  - vintagetech:fan
position: 8
---

# Fan

A powered mechanical consumer that uses rotation to create an airflow to push entities

If inverted the rotation it will invert the direction of the current airflow

<GameScene zoom="3" interactive={true}>
  <Block x="1" y="0" z="0" id="vintagetech:fan" p:enabled="true"  p:facing="east" p:inverted="false"/>
  <Block x="0" y="0" z="0" id="vintagetech:axle" p:axis="x" p:enabled="true" p:has_rope="false" p:inverted="false"/>

  <Block x="1" y="0" z="2" id="vintagetech:fan" p:enabled="true"  p:facing="east" p:inverted="true"/>
  <Block x="0" y="0" z="2" id="vintagetech:axle" p:axis="x" p:enabled="true" p:has_rope="false" p:inverted="true"/>
</GameScene>


<RecipeFor id="vintagetech:fan" />