package controllers;

import play.db.Model;
import play.i18n.Lang;
import play.i18n.Messages;

public abstract class CrudKesako extends CRUD {

	public static ObjectTypeKesako createObjectType(Class<? extends Model> classePersistante) {
		return new ObjectTypeKesako(classePersistante);
	}
	
	public static class ObjectTypeKesako extends CRUD.ObjectType implements Comparable<ObjectType> {
		public String descriptionIndex;
		private String titlename1;
		private String description2;
		public ObjectTypeKesako(Class<? extends Model> modelClass) {
			super(modelClass);
			this.descriptionIndex = Messages.getMessage(Lang.get(), "crud.descriptionIndex." + modelName, new Object[] {});
			this.titlename1 = Messages.getMessage(Lang.get(), "crud.titlename1." + modelName, new Object[] {});
			this.description2 = Messages.getMessage(Lang.get(), "crud.description2." + modelName, new Object[] {});
		}
	}
}