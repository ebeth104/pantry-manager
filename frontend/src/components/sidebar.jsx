import React, { useEffect, useState } from "react";
import { ChevronsLeft, Grid, Search } from "react-feather";
import {
  MdOutlineSpaceDashboard,
  MdOutlineCategory,
  MdOutlineSettings,
} from "react-icons/md";
import { PiBowlFoodDuotone } from "react-icons/pi";
import { CgProfile } from "react-icons/cg";
import { AiOutlineLogout } from "react-icons/ai";
import { FaChevronUp, FaPlus } from "react-icons/fa6";

function Sidebar() {
  const [open, setOpen] = useState(true);
  const [subMenuOpen, setSubMenuOpen] = useState(false);
  const Menu = [
    { title: "Dashboard", icon: <MdOutlineSpaceDashboard /> },
    {
      title: "Category",
      icon: <MdOutlineCategory />,
      subMenu: true,
      subMenuItems: [{ title: "Add Category", icon: <FaPlus /> }],
    },
    { title: "Recipes", icon: <PiBowlFoodDuotone /> },
    { title: "Profile", spacing: true, icon: <CgProfile /> },
    { title: "Settings", icon: <MdOutlineSettings /> },
    { title: "Log Out", icon: <AiOutlineLogout /> },
  ];

  return (
    <>
      <div className="flex">
        <div
          className={`bg-green-300 h-screen p-4 pt-8 ${
            open ? "w-72" : "w-20"
          } duration-300 relative`}
        >
          <div
            className={`flex ${open ? "flex-row" : "flex-col"} items-center`}
          >
            <Grid className="rounded block" size={30} />
            <h1
              className={`origin-left font-medium text-2xl duration-300 ml-2 mr-2 ${
                !open && "scale-0"
              }`}
            >
              PANTRY
            </h1>
            <ChevronsLeft
              className={`bg-green-200 text-4xl rounded-full cursor-pointer ${
                !open && "rotate-180"
              } ${open ? "ml-auto" : ""}`}
              onClick={() => setOpen(!open)}
              size={32}
            />
          </div>
          <div className="flex item-center rounded-md bg-green-100 mt-6 px-2 py-4">
            <Search
              className="text-gray-400 block float-left cursor-pointer"
              onClick={() => setOpen(true)}
            />
            <input
              type="search"
              placeholder="Search"
              className={`text-base ml-2 bg-transparent w-full focus:outline-none ${
                !open && "hidden"
              }`}
            />
          </div>
          <div>
            <ul className="pt-2">
              {Menu.map((menu, index) => (
                <>
                  <li
                    className={`text-sm flex items-center gap-x-4 cursor-pointer p-2 rounded-md hover:bg-green-200 ${
                      menu.spacing ? "mt-80" : "mt-2"
                    }`}
                  >
                    <span className="text-lg block float-left">
                      {menu.icon}
                    </span>
                    <span
                      className={`text-base font-medium flex-1 duration-300 ${
                        !open && "hidden"
                      }`}
                    >
                      {menu.title}
                    </span>
                    <span>
                      {menu.subMenu && open && (
                        <FaChevronUp
                          className={`${!subMenuOpen && "rotate-180"}`}
                          onClick={() => setSubMenuOpen(!subMenuOpen)}
                        />
                      )}
                    </span>
                  </li>
                  {menu.subMenu && subMenuOpen && open && (
                    <ul>
                      {menu.subMenuItems.map((category, index) => (
                        <li
                          key={index}
                          className={`text-sm flex items-center gap-x-4 cursor-pointer p-2 rounded-md hover:bg-green-200 px-10`}
                        >
                          <span className="text-slate-950/50 text-lg block float-left">
                            {category.icon}
                          </span>
                          <span>{category.title}</span>
                        </li>
                      ))}
                    </ul>
                  )}
                </>
              ))}
            </ul>
          </div>
        </div>
      </div>
    </>
  );
}

export default Sidebar;
