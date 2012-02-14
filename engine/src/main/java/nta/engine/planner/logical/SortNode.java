/**
 * 
 */
package nta.engine.planner.logical;

import com.google.gson.annotations.Expose;

import nta.engine.json.GsonCreator;
import nta.engine.parser.QueryBlock.SortKey;
import nta.engine.utils.TUtil;

/**
 * @author Hyunsik Choi
 *
 */
public final class SortNode extends UnaryNode implements Cloneable {
	@Expose
  private SortKey [] sortKeys;
	
	public SortNode() {
		super();
	}
  
  /**
   * @param opType
   */
  public SortNode(SortKey [] sortKeys) {
    super(ExprType.SORT);
    this.sortKeys = sortKeys;
  }
  
  public SortKey [] getSortKeys() {
    return this.sortKeys;
  }
  
  @Override 
  public boolean equals(Object obj) {
    if (obj instanceof SortNode) {
      SortNode other = (SortNode) obj;
      return super.equals(other)
          && TUtil.checkEquals(sortKeys, other.sortKeys)
          && subExpr.equals(other.subExpr);
    } else {
      return false;
    }
  }
  
  @Override
  public Object clone() throws CloneNotSupportedException {
    SortNode sort = (SortNode) super.clone();
    sort.sortKeys = sortKeys.clone();
    
    return sort;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder("Order By ");
    for (int i = 0; i < sortKeys.length; i++) {    
      sb.append(sortKeys[i].getSortKey().getName()+" "+
          (sortKeys[i].isAscending() ? "asc" : "desc"));
      if(i < sortKeys.length - 1) {
        sb.append(",");
      }
    }
    return sb.toString()+"\n"
        + getSubNode().toString();
  }
  
  public String toJSON() {
    return GsonCreator.getInstance().toJson(this, LogicalNode.class);
  }
}
