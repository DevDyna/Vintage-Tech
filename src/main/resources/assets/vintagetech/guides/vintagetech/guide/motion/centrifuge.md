---
navigation:
  title: Centrifuge
  icon: "vintagetech:centrifuge"
  parent: motion.md
categories:
  - consumer
item_ids:
  - vintagetech:centrifuge
position: 7
---

# Centrifuge

A powered processing machine that uses rotational motion to process fluids with items

It has 2 slots

Can execute multiple recipes at same time when fluid amount is the exact amount stored

Can recieve fluids extracted by <ItemLink id="vintagetech:tree_tap"/>

<GameScene zoom="3" interactive={true}>
  <Block x="0" y="1" z="0" id="vintagetech:centrifuge" p:enabled="true" p:inverted="false"/>
  <Block x="0" y="0" z="0" id="vintagetech:axle" p:axis="y" p:enabled="true" p:has_rope="false" p:inverted="false"/>
</GameScene>

<RecipeFor id="vintagetech:centrifuge" />