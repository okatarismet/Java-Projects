import java.util.List;
import java.util.ArrayList;


public interface IDataAccessObject {

    /**
     *  Read a single entry from file
     * @param ID id of the customer
     * @return  Object csutomer with given ID
     */
            Object getByID(int ID);

    /**
     *   Delete a single entry from file
     * @param ID  id of customer
     * @return result of operation
     */
            boolean deleteByID(int ID);

    /**
     *  Add a new object
     * @param object  object to be added
     */
            void 	add(Object object);

    /**
     *  Update an entry
     * @param object   entry to be updated
     */
            void 	update(Object object);

    /**
     * Returns all entries  in a list
     * @return   List list of all entries
     */
            ArrayList getALL(); // get all entries

}
